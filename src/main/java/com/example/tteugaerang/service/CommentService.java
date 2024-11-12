package com.example.tteugaerang.service;

import com.example.tteugaerang.domain.Comment;
import com.example.tteugaerang.domain.Community;

public interface CommentService {
    public void create(Community community, String content);
    public Comment getComment(Long id);
    public void modify(Comment comment, String content);
    public void delete(Comment comment);
}
