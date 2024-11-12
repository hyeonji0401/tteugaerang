package com.example.tteugaerang.controller;


import com.example.tteugaerang.domain.Comment;
import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommentFormDTO;
import com.example.tteugaerang.repository.CommentRepository;
import com.example.tteugaerang.service.CommentService;
import com.example.tteugaerang.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommunityService communityService;
    private final CommentService commentService;
    @PostMapping("/community/comment/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @RequestParam(value = "content") String content){
        Community community = this.communityService.getCommunity(id);
        this.commentService.create(community, content);
        return String.format("redirect:/community/detail/%s", id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/comment/modify/{id}")
    public String commentModify(CommentFormDTO commentFormDTO, @PathVariable("id") Long id, Principal principal){
        Comment comment = this.commentService.getComment(id);
        if(!comment.getWriter().getEmail().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        commentFormDTO.setContent(comment.getContent());
        return "comment_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/modify/{id}")
    public String commentModify(@Valid CommentFormDTO commentFormDTO, @PathVariable("id") Long id, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "comment_form";
        }
        Comment comment = this.commentService.getComment(id);
        if(!comment.getWriter().getEmail().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        this.commentService.modify(comment, commentFormDTO.getContent());
        return String.format("redirect:/community/detail/%s", comment.getCommunity().getId());
    }


    @PostMapping("/comment/delete/{id}")
    public String delete(Principal principal,  @PathVariable("id") Long id){
        Comment comment = this.commentService.getComment(id);
        if(!comment.getWriter().getEmail().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다");
        }
        this.commentService.delete(comment);
        return String.format("redirect:/community/detail/%s", comment.getCommunity().getId());
    }
}
