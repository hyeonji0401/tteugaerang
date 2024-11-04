package com.example.tteugaerang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String main() {
        return "/main";
    }

    @GetMapping("/ai")
    public String ai(){
        return "/ai";
    }

    @GetMapping("/shop")
    public String shop(){
        return "/shop";
    }

    @GetMapping("/design")
    public String design(){
        return "/design";
    }

}
