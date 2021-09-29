package com.collection.words.wordscollectionswebservice.domain.memorize;

import com.collection.words.wordscollectionswebservice.web.dto.MemorizeListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemorizeRepository extends JpaRepository<Memorize, Long> {
    List<Memorize> findByEmail(String email);
    Memorize findByEmailAndWord(String email,String word);
}