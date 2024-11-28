package com.example.tteugaerang.service.impl;

import com.example.tteugaerang.domain.Member;
import com.example.tteugaerang.dto.MemberDTO;
import com.example.tteugaerang.repository.MemberRepository;
import com.example.tteugaerang.service.MemberService;
import com.example.tteugaerang.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final UserSecurityService userSecurityService;

    @Override
    public Member create(MemberDTO memberDTO){
        if(memberRepository.existsByEmail(memberDTO.getEmail())){
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member member = new Member();
        member.setMemberName(memberDTO.getMemberName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setUserRole("ROLE_USER");
        return memberRepository.save(member);
    }

    @Override
    public MemberDTO showMember(){
        Member member = this.userSecurityService.getAuthen();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(member.getMemberName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPassword(member.getPassword());
        return memberDTO;
    }


}
