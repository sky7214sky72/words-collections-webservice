package com.collection.words.wordscollectionswebservice.domain.memorization;

import com.collection.words.wordscollectionswebservice.domain.BaseTimeEntity;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Entity
public class Memorization extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="WORDS_ID")
    private ToeicWords toeicWords;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Builder
    public Memorization(ToeicWords toeicWords, User user){
        this.toeicWords = toeicWords;
        this.user = user;
    }
}
