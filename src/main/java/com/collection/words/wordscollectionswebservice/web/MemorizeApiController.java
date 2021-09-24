package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.memorize.MemorizeService;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizeListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemorizeApiController {
    private final MemorizeService memorizeService;

    @PostMapping("/api/memorize/save")
    public Long save(@RequestBody MemorizeSaveRequestDto requestDto){
        return memorizeService.save(requestDto);
    }

    @PostMapping("/api/memorize/read")
    public List<MemorizeListResponseDto> read(@RequestBody String email){
        return memorizeService.findByEmail(email);
    }
}
