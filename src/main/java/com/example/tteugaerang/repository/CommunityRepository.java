package com.example.tteugaerang.repository;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommunityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Long> {
    Page<Community>findAll(Pageable pageable);
    Page<Community>findAll(Specification<Community> spec, Pageable pageable);
}
