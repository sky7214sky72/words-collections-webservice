package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpResponse;

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
        model.addAttribute("posts",postsService.findAllRand());
        return "wordlist";
    }

    @PostMapping("/api/words/searchCategory")
    public String searchCategory(Model model, @RequestParam("category") String category){
        model.addAttribute("posts",postsService.findByCategory(category));
        return "wordlist";
    }

    @GetMapping("/api/words/save")
    public String wordSave(){
        return "savepage";
    }

    @GetMapping("/api/video/list")
    public String videoPage(){
        return "videopage";
    }

    @GetMapping("/api/chat/list")
    public String chatPage(){
        return "chatpage";
    }

}
