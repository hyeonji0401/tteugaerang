package com.example.tteugaerang.service.impl;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.domain.Member;
import com.example.tteugaerang.dto.CommunityDTO;
import com.example.tteugaerang.dto.CommunityFormDTO;
import com.example.tteugaerang.exception.DataNotFoundException;
import com.example.tteugaerang.repository.CommunityRepository;
import com.example.tteugaerang.service.CommunityService;
import com.example.tteugaerang.service.UserSecurityService;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final UserSecurityService userSecurityService;

    //글 생성
    @Override
    public Community create(CommunityFormDTO communityFormDTO) {
        try {
            Community community = new Community();
            community.setTitle(communityFormDTO.getTitle());
            community.setContent(communityFormDTO.getContent());
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
    public Page<Community> getList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("writeTime"));
        Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
        Specification<Community> spec = search(kw);
        return this.communityRepository.findAll(spec, pageable);
    }

    //글 상세 조회
    @Override
    public Community getCommunity(Long id){
        Optional<Community> community = this.communityRepository.findById(id);
        if(community.isPresent()){
            return community.get();
        }else{
            throw new DataNotFoundException("post not found");
        }

    }

    //글 검색
    private Specification<Community> search(String kw){
        return new Specification<Community>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Community> c, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Community, Member> u1 = c.join("writer", JoinType.LEFT);
                return cb.or(cb.like(c.get("title"), "%" + kw + "%"),
                        cb.like(c.get("content"), "%"+kw+"%"),
                        cb.like(u1.get("memberName"), "%"+kw+"%"));
            }
        };
    }

    @Override
    public void modify(Community community, String title, String content){
        community.setTitle(title);
        community.setContent(content);
        community.setUpdateTime(LocalDateTime.now());
        this.communityRepository.save(community);
    }
}
