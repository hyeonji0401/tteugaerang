package com.example.tteugaerang.controller;

import com.example.tteugaerang.dto.LoginDTO;
import com.example.tteugaerang.dto.MemberDTO;
import com.example.tteugaerang.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberDTO memberDTO){
        return "/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberDTO memberDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/signup";
        }

        try {
            memberService.create(memberDTO);
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "/signup";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/signup";
        }
        return "/main";
    }

    @GetMapping("/login")
    public String signup(){return "/login";}



}
