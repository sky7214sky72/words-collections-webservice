package com.collection.words.wordscollectionswebservice.service.memorize;

import com.collection.words.wordscollectionswebservice.domain.memorize.Memorize;
import com.collection.words.wordscollectionswebservice.domain.memorize.MemorizeRepository;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizeListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemorizeService {
    private final MemorizeRepository memorizeRepository;

    @Transactional
    public Long save(MemorizeSaveRequestDto requestDto){
        return memorizeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<MemorizeListResponseDto> findByEmail(String email){
        return memorizeRepository.findByEmail(email).stream().map(MemorizeListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (String email, String word){
        Memorize memorize = memorizeRepository.findByEmailAndWord(email,word);

        memorizeRepository.delete(memorize);
    }
}
