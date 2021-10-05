package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import lombok.Getter;

import java.util.Optional;

@Getter
public class MemorizationListResponseDto {
    private Long word_id;
    private Long user_id;

    public MemorizationListResponseDto(Memorization entity){
        this.word_id = entity.getToeicWords().getId();
        this.user_id = entity.getUser().getId();
    }
}
