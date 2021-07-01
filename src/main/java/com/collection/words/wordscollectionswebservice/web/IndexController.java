package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/api/words/list")
    public String wordList(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "wordlist";
    }

    @GetMapping("/api/words/save")
    public String wordSave(){
        return "savepage";
    }
}
