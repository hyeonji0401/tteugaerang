package com.example.tteugaerang.service;

import com.example.tteugaerang.domain.Member;
import com.example.tteugaerang.dto.MemberDTO;

public interface MemberService {
    Member create(MemberDTO memberDTO);
}
