package com.collection.words.wordscollectionswebservice.domain.toeic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ToeicWordsRepository extends JpaRepository<ToeicWords, Long> {
    @Query(value = "SELECT p.* FROM Posts p", nativeQuery = true)
    Page<ToeicWords> findAllRand(Pageable pageable);
}
