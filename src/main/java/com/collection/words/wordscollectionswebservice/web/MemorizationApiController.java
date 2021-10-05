package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.config.auth.LoginUser;
import com.collection.words.wordscollectionswebservice.config.auth.dto.SessionUser;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWords;
import com.collection.words.wordscollectionswebservice.domain.toeic.ToeicWordsRepository;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import com.collection.words.wordscollectionswebservice.domain.user.UserRepository;
import com.collection.words.wordscollectionswebservice.service.memorization.MemorizationService;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemorizationApiController {

    private final MemorizationService memorizationService;

    @PostMapping("/api/memorize/save")
    public Long save(@RequestBody Long word_id,@LoginUser SessionUser user){
        Long user_id = user.getUser_id();
        return memorizationService.save(word_id,user_id);
    }

    @PostMapping("/api/memorize/read")
    public List<MemorizationListResponseDto> read(@LoginUser SessionUser user){
        Long user_id = user.getUser_id();
        return memorizationService.findByUser_Id(user_id);
    }

    @DeleteMapping("/api/memorize/{wordId}")
    public Long delete(@LoginUser SessionUser user,@PathVariable Long wordId){
        Long userId = user.getUser_id();
        memorizationService.delete(userId,wordId);
        return userId;
    }
}
