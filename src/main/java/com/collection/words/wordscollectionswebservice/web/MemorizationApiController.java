package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.memorization.MemorizationService;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemorizationApiController {
    private final MemorizationService memorizationService;

    @PostMapping("/api/memorize/save")
    public Long save(@RequestBody MemorizationSaveRequestDto requestDto){
        return memorizationService.save(requestDto);
    }

    @PostMapping("/api/memorize/read")
    public List<MemorizationListResponseDto> read(@RequestBody String email){
        return memorizationService.findByEmail(email);
    }

    @DeleteMapping("/api/memorize/{email}/{word}")
    public String delete(@PathVariable String email,@PathVariable String word){
        memorizationService.delete(email,word);
        return email;
    }
}
