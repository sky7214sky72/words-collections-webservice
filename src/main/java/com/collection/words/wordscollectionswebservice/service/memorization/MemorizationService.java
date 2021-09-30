package com.collection.words.wordscollectionswebservice.service.memorization;

import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import com.collection.words.wordscollectionswebservice.domain.memorization.MemorizationRepository;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemorizationService {
    private final MemorizationRepository memorizationRepository;

    @Transactional
    public Long save(MemorizationSaveRequestDto requestDto){
        return memorizationRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<MemorizationListResponseDto> findByEmail(String email){
        return memorizationRepository.findByEmail(email).stream().map(MemorizationListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (String email, String word){
        Memorization memorization = memorizationRepository.findByEmailAndWord(email,word);

        memorizationRepository.delete(memorization);
    }

    @Transactional
    public long memoCount(String email){
        return memorizationRepository.findByEmail(email).stream().count();
    }
}
