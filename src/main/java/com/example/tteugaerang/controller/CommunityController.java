package com.example.tteugaerang.controller;

import com.example.tteugaerang.dto.CommunityDTO;
import com.example.tteugaerang.dto.CommunityFormDTO;
import com.example.tteugaerang.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    //전체 조회
    @GetMapping("/community")
    public String list(Model model){
        List<CommunityDTO> communityList = this.communityService.findAllCommunity();
        model.addAttribute("communityList", communityList);
        return "community";
    }

    @GetMapping("/community/create")
    public String create(CommunityFormDTO communityFormDTO){
        return "write_post";
    }

    //글 생성
    @PostMapping("/community/create")
    public String create(@Valid CommunityFormDTO communityFormDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "write_post";
        }
        this.communityService.create(communityFormDTO);
        return "redirect:/community";
    }

    //삭제
    @GetMapping("/community/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        communityService.delete(id);
        return "redirect:/community";
    }

}
