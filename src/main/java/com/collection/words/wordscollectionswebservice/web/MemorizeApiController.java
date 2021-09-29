package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.memorize.MemorizeService;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizeListResponseDto;
import com.collection.words.wordscollectionswebservice.web.dto.MemorizeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/api/memorize/{email}/{word}")
    public String delete(@PathVariable String email,@PathVariable String word){
        memorizeService.delete(email,word);
        return email;
    }
}
