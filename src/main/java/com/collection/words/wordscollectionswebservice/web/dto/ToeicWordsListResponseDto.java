package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import lombok.Getter;

@Getter
public class ToeicWordsListResponseDto {
    private Long id;
    private String word;
    private String meaning;

    public ToeicWordsListResponseDto(ToeicWords entity){
        this.id = entity.getId();
        this.word = entity.getWord();
        this.meaning = entity.getMeaning();
    }
}
