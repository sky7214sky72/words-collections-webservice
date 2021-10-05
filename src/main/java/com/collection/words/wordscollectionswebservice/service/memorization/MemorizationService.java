package com.collection.words.wordscollectionswebservice.service.memorization;

import com.collection.words.wordscollectionswebservice.config.auth.dto.SessionUser;
import com.collection.words.wordscollectionswebservice.domain.memorization.Memorization;
import com.collection.words.wordscollectionswebservice.domain.memorization.MemorizationRepository;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWordsRepository;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import com.collection.words.wordscollectionswebservice.domain.user.UserRepository;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemorizationService {
    private final MemorizationRepository memorizationRepository;
    private final ToeicWordsRepository toeicWordsRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long save(Long word_id, Long user_id){
        MemorizationSaveRequestDto requestDto = new MemorizationSaveRequestDto();
        ToeicWords toeicWords = toeicWordsRepository.findById(word_id).orElseThrow(()->new IllegalArgumentException("없습니다. id="+word_id));
        User userInfo = userRepository.findById(user_id).orElseThrow(()->new IllegalArgumentException("없습니다. id="+user_id));
        requestDto = MemorizationSaveRequestDto.builder()
                .user(userInfo)
                .toeicWords(toeicWords)
                .build();
        return memorizationRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<MemorizationListResponseDto> findByUser_Id(Long user_id){
        return memorizationRepository.findByUser_Id(user_id).stream().map(MemorizationListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long user_id, Long word_id){
        Memorization memorization = memorizationRepository.findByUser_IdAndToeicWords_Id(user_id,word_id);

        memorizationRepository.delete(memorization);
    }

    @Transactional
    public long memoCount(Long user_id){
        return memorizationRepository.findByUser_Id(user_id).stream().count();
    }
}
