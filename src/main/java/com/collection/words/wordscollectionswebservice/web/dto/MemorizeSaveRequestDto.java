package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.memorize.Memorize;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemorizeSaveRequestDto {
    private String word;
    private String email;
    private String name;

    @Builder
    public MemorizeSaveRequestDto(String word, String email,String name){
        this.word = word;
        this.email = email;
        this.name = name;
    }

    public Memorize toEntity(){
        return Memorize.builder()
                .word(word)
                .email(email)
                .name(name)
                .build();
    }
}
