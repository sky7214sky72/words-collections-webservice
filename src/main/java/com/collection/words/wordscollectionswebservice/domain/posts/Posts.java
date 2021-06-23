package com.collection.words.wordscollectionswebservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String word;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String meaning;

    private String category;

    @Builder
    public Posts(String word, String meaning, String category){
        this.word = word;
        this.meaning = meaning;
        this.category = category;
    }
}
