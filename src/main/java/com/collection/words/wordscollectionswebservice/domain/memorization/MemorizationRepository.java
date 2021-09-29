package com.collection.words.wordscollectionswebservice.domain.memorization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemorizationRepository extends JpaRepository<Memorization, Long> {
    List<Memorization> findByEmail(String email);
    Memorization findByEmailAndWord(String email, String word);
}