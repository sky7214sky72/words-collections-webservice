package com.collection.words.wordscollectionswebservice.domain.posts;

import com.collection.words.wordscollectionswebservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String word;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String meaning;

    @Builder
    public Posts(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }
}
