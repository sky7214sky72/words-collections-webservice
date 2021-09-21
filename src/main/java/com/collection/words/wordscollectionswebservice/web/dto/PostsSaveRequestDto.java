package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String word;
    private String meaning;
    @Builder
    public PostsSaveRequestDto(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    public Posts toEntity(){
        return Posts.builder()
                .word(word)
                .meaning(meaning)
                .build();
    }
}
