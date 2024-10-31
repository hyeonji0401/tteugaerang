package com.example.tteugaerang.service;

import com.example.tteugaerang.domain.Community;
import com.example.tteugaerang.dto.CommunityDTO;

import java.util.List;

public interface CommunityService {
    Community create(CommunityDTO communityDTO);
    void delete(Long communityId);

    List<CommunityDTO>findAllCommunity();

}
