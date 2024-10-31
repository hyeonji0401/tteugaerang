package com.example.tteugaerang.service.impl;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommunityDTO;
import com.example.tteugaerang.repository.CommunityRepository;
import com.example.tteugaerang.service.CommunityService;
import com.example.tteugaerang.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final UserSecurityService userSecurityService;

    //글 생성
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

    //글 삭제
    @Override
    public void delete(Long communityId){
        Optional<Community> communityOpt = this.communityRepository.findById(communityId);
        Community community= communityOpt.orElseThrow(() -> new NoSuchElementException("Post not found"));
        if(!userSecurityService.getAuthen().equals(community.getWriter())){
            throw new SecurityException("You are not the owner of this post");
        }
        this.communityRepository.delete(community);

    }

    //글 전체 조회
    @Override
    public List<CommunityDTO> findAllCommunity(){
        List<Community> communityList = this.communityRepository.findAll();

        return communityList.stream()
                .map(CommunityDTO::new)
                .collect(Collectors.toList());


    }
}
