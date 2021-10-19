package com.collection.words.wordscollectionswebservice.web;

import com.collection.words.wordscollectionswebservice.config.auth.LoginUser;
import com.collection.words.wordscollectionswebservice.config.auth.dto.SessionUser;
import com.collection.words.wordscollectionswebservice.service.memorization.MemorizationService;
import com.collection.words.wordscollectionswebservice.service.toeic.ToeicWordsService;
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

    private final ToeicWordsService toeicWordsService;
    private final MemorizationService memorizationService;

    @GetMapping("/")
    public String wordList(Model model,@LoginUser SessionUser user, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        int pageCount = toeicWordsService.pageCount(pageable);
        int pageNumber = toeicWordsService.findAll(pageable).getNumber() + 1;
        Long totalCount = toeicWordsService.findAll(pageable).getTotalElements();
        if (user != null){
            if(user.getRole().equals("ROLE_ADMIN")){
                model.addAttribute("adminEmail",user.getEmail());
            }else{
                model.addAttribute("guestEmail",user.getEmail());
            }
        }
        model.addAttribute("toeicWords", toeicWordsService.findAll(pageable));
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

    @GetMapping("/api/my/progress")
    public String wordProgress(Model model,@LoginUser SessionUser user, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        if (user != null){
            model.addAttribute("guestEmail",user.getEmail());
            model.addAttribute("guestName",user.getName());
        }
        Long totalCount = toeicWordsService.findAll(pageable).getTotalElements();
        Long memoCount = memorizationService.memoCount(user.getUser_id());
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("memoCount",memoCount);
        return "progresspage";
    }

}
