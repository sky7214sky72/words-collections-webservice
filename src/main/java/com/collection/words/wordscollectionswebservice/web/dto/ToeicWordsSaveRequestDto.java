package com.collection.words.wordscollectionswebservice.web.dto;

import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToeicWordsSaveRequestDto {
    private String word;
    private String meaning;
    @Builder
    public ToeicWordsSaveRequestDto(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    public ToeicWords toEntity(){
        return ToeicWords.builder()
                .word(word)
                .meaning(meaning)
                .build();
    }
}
