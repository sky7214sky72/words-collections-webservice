package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.domain.posts.Posts;
import com.collection.words.wordscollectionswebservice.domain.posts.PostsRepository;
import com.collection.words.wordscollectionswebservice.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록() throws Exception{
        String word = "Test";
        String meaning = "시험";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .word(word)
                .meaning(meaning)
/*                .category("part1")*/
                .build();

        String url = "http://localhost:"+ port + "/api/words/posts";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getWord()).isEqualTo(word);
        assertThat(all.get(0).getMeaning()).isEqualTo(meaning);
    }
}
