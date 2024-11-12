package com.example.tteugaerang.repository;

import com.example.tteugaerang.domain.Comment;
import com.example.tteugaerang.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment,Long> {
}
