package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.posts.PostsService;
import com.collection.words.wordscollectionswebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/words/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PostMapping("/api/words/postsExcel")
    public void excelsave(@RequestParam("file") MultipartFile file) throws IOException {
        postsService.excelSave(file);
    }
}
