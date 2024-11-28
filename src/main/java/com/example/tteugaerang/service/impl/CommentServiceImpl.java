package com.example.tteugaerang.service.impl;

import com.example.tteugaerang.domain.Comment;
import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.exception.DataNotFoundException;
import com.example.tteugaerang.repository.CommentRepository;
import com.example.tteugaerang.service.CommentService;
import com.example.tteugaerang.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final UserSecurityService userSecurityService;
    private final CommentRepository commentRepository;

    @Override
    public void create(Community community, String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommunity(community);
        comment.setWriter(userSecurityService.getAuthen());
        comment.setWriteTime(LocalDateTime.now());

        this.commentRepository.save(comment);

    }

    @Override
    public Comment getComment(Long id){
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(comment.isPresent()){
            return comment.get();
        }
        else{
            throw new DataNotFoundException("comment not found");
        }
    }

    @Override
    public void modify(Comment comment, String content){
        comment.setContent(content);
        comment.setUpdateTime(LocalDateTime.now());
        this.commentRepository.save(comment);

    }

    @Override
    public void delete(Comment comment){
        this.commentRepository.delete(comment);
    }
}
