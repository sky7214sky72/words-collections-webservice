package com.collection.words.wordscollectionswebservice.domain.toeic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ToeicWordsRepositoryTest {

    @Autowired
    ToeicWordsRepository toeicWordsRepository;

    @AfterEach
    public void cleanup(){
        toeicWordsRepository.deleteAll();
    }


    @Test
    public void 단어_불러오기(){
        String word = "Test";
        String meaning = "시험";
        toeicWordsRepository.save(ToeicWords.builder().word(word).meaning(meaning).build());

        List<ToeicWords> toeicWordsList = toeicWordsRepository.findAll();

        ToeicWords toeicWords = toeicWordsList.get(0);
        assertThat(toeicWords.getWord()).isEqualTo(word);
        assertThat(toeicWords.getMeaning()).isEqualTo(meaning);
    }

}
