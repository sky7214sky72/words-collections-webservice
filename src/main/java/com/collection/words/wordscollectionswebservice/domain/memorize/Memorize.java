package com.collection.words.wordscollectionswebservice.domain.memorize;

import com.collection.words.wordscollectionswebservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Memorize extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String word;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Builder
    public Memorize(String word, String email,String name){
        this.word = word;
        this.email = email;
        this.name = name;
    }
}
