package com.example.tteugaerang.controller;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommunityDTO;
import com.example.tteugaerang.dto.CommunityFormDTO;
import com.example.tteugaerang.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    //전체 조회
    @GetMapping("/community")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page,
                       @RequestParam(value="kw", defaultValue = "") String kw){
        Page<Community> paging = this.communityService.getList(page,kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "community";
    }

    //상세 조회
    @GetMapping(value="/community/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        Community community = this.communityService.getCommunity(id);
        model.addAttribute("community", community);
        return "post";
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
    @PostMapping("/community/delete/{id}")
    public String delete(Principal principal,  @PathVariable("id") Long id){
        Community community = this.communityService.getCommunity(id);
        if(!community.getWriter().getEmail().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다");
        }
        this.communityService.delete(id);
        return "redirect:/community";
    }

    //글 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/community/modify/{id}")
    public String communityModify(CommunityFormDTO communityFormDTO, @PathVariable("id") Long id, Principal principal){
        Community community = this.communityService.getCommunity(id);
        if(!community.getWriter().getEmail().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        communityFormDTO.setTitle(community.getTitle());
        communityFormDTO.setContent(community.getContent());
        return "write_post";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/community/modify/{id}")
    public String communityModify(@Valid CommunityFormDTO communityFormDTO, BindingResult bindingResult, Principal principal, @PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "write_post";
        }
        Community community = this.communityService.getCommunity(id);
        if(!community.getWriter().getEmail().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        this.communityService.modify(community, communityFormDTO.getTitle(), communityFormDTO.getContent());
        return String.format("redirect:/community/detail/%s", id);
    }

}
