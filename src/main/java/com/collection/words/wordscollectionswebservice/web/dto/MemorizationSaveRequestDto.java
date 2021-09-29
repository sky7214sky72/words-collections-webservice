package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemorizationSaveRequestDto {
    private String word;
    private String email;
    private String name;

    @Builder
    public MemorizationSaveRequestDto(String word, String email, String name){
        this.word = word;
        this.email = email;
        this.name = name;
    }

    public Memorization toEntity(){
        return Memorization.builder()
                .word(word)
                .email(email)
                .name(name)
                .build();
    }
}
