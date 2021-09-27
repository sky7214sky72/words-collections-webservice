package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.config.auth.LoginUser;
import com.collection.words.wordscollectionswebservice.config.auth.dto.SessionUser;
import com.collection.words.wordscollectionswebservice.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;


    @GetMapping("/")
    public String wordList(Model model,@LoginUser SessionUser user, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        int pageCount = postsService.pageCount(pageable);
        int pageNumber = postsService.findAll(pageable).getNumber() + 1;
        Long totalCount = postsService.findAll(pageable).getTotalElements();
        if (user != null){
            if(user.getRole().equals("ROLE_ADMIN")){
                model.addAttribute("adminName",user.getEmail());
                model.addAttribute("adName",user.getName());
            }else{
                model.addAttribute("guestName",user.getEmail());
                model.addAttribute("guName",user.getName());
            }
        }
        model.addAttribute("posts",postsService.findAll(pageable));
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("totalCount",totalCount);
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
