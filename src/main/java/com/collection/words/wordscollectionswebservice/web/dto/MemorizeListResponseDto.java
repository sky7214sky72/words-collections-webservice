package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.memorize.Memorize;
import lombok.Getter;

@Getter
public class MemorizeListResponseDto {
    private String word;
    private String email;
    private String name;

    public MemorizeListResponseDto(Memorize entity){
        this.word = entity.getWord();
        this.email = entity.getEmail();
        this.name = entity.getName();
    }
}
