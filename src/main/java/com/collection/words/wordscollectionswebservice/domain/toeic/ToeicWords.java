package com.collection.words.wordscollectionswebservice.domain.toeic;

import com.collection.words.wordscollectionswebservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ToeicWords{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORDS_ID")
    private Long id;

    @Column(length = 500, nullable = false)
    private String word;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String meaning;

    @Builder
    public ToeicWords(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }
}
