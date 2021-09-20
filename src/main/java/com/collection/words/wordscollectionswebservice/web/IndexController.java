package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.posts.PostsService;
import com.collection.words.wordscollectionswebservice.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

/*    @GetMapping("/")
    public String index(){
        return "index";
    }*/

    @GetMapping("/")
    public String wordList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        int pageCount = postsService.pageCount(pageable);
        int pageNumber = postsService.findAllRand(pageable).getNumber() + 1;
        model.addAttribute("posts",postsService.findAllRand(pageable));
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("pageNumber",pageNumber);
        return "wordlist";
    }

    @GetMapping("/api/words/save")
    public String wordSave(){
        return "savepage";
    }

    @GetMapping("/api/chat/list")
    public String chatPage(){
        return "chatpage";
    }

}
