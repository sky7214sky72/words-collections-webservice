package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class MemorizationSaveRequestDto {
    private ToeicWords toeicWords;
    private User user;

    @Builder
    public MemorizationSaveRequestDto(ToeicWords toeicWords, User user){
        this.toeicWords = toeicWords;
        this.user = user;
    }

    public Memorization toEntity(){
        return Memorization.builder()
                .toeicWords(toeicWords)
                .user(user)
                .build();
    }
}
