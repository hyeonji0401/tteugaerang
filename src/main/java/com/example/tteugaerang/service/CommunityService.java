package com.example.tteugaerang.service;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommunityDTO;
import com.example.tteugaerang.dto.CommunityFormDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommunityService {
    Community create(CommunityFormDTO communityFormDTO);
    void delete(Long communityId);
    Page<Community> getList(int page);

}
