package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String word;
    private String meaning;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.word = entity.getWord();
        this.meaning = entity.getMeaning();
    }
}
