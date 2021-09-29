package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import lombok.Getter;

@Getter
public class MemorizationListResponseDto {
    private String word;
    private String email;
    private String name;

    public MemorizationListResponseDto(Memorization entity){
        this.word = entity.getWord();
        this.email = entity.getEmail();
        this.name = entity.getName();
    }
}
