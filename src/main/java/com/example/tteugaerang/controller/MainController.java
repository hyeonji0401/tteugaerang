package com.example.tteugaerang.controller;

import com.example.tteugaerang.dto.MemberDTO;
import com.example.tteugaerang.service.MemberService;
import com.example.tteugaerang.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/ai")
    public String ai(){
        return "ai";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/design")
    public String design(){
        return "design";
    }

    @GetMapping("/mypage")
    public String myPage(Model model){
        MemberDTO member = this.memberService.showMember();
        model.addAttribute("member", member);
        return "mypage";
    }

}
