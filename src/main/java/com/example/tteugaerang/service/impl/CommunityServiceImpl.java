package com.example.tteugaerang.service.impl;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommunityDTO;
import com.example.tteugaerang.repository.CommunityRepository;
import com.example.tteugaerang.service.CommunityService;
import com.example.tteugaerang.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final UserSecurityService userSecurityService;

    @Override
    public Community create(CommunityDTO communityDTO) {
        try {
            Community community = new Community();
            community.setTitle(communityDTO.getTitle());
            community.setContent(communityDTO.getContent());
            community.setWriter(userSecurityService.getAuthen());
            community.setWriteTime(LocalDateTime.now());
            return this.communityRepository.save(community);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("입력값이 null입니다.", e);
        }
    }

    @Override
    public void delete(Long communityId){
        Optional<Community> communityOpt = this.communityRepository.findById(communityId);
        Community community= communityOpt.orElseThrow(() -> new NoSuchElementException("Post not found"));
        if(!userSecurityService.getAuthen().equals(community.getWriter())){
            throw new SecurityException("You are not the owner of this post");
        }
        this.communityRepository.delete(community);

    }
}
