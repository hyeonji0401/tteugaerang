package com.example.tteugaerang.controller;

import com.example.tteugaerang.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/community/create")
    public String create(@RequestParam(value="title") String title, @RequestParam(value="content") String content){
        return "redirect:/community";
    }

    @GetMapping("/community/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        communityService.delete(id);
        return "redirect:/community";
    }

}
