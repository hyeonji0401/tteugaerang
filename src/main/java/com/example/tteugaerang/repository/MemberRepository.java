package com.example.tteugaerang.repository;

import com.example.tteugaerang.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmail(String email);
    Optional<Member>findByEmail(String email);
}
