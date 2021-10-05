package com.collection.words.wordscollectionswebservice.domain.memorization;

import com.collection.words.wordscollectionswebservice.config.auth.dto.SessionUser;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemorizationRepository extends JpaRepository<Memorization, Long> {
    //List<Memorization> findByEmail(User user);
    //Memorization findByEmailAndWord(String email, String word);
    List<Memorization> findByUser_Id(Long user_id);
    Memorization findByUser_IdAndToeicWords_Id(Long user_id, Long word_id);
}