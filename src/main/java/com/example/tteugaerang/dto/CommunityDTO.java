package com.example.tteugaerang.dto;
import com.example.tteugaerang.domain.Community;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommunityDTO {
    private String writer;
    private String title;
    private String content;
    private LocalDateTime writeTime;

    public CommunityDTO(Community community){
        this.writer = community.getWriter().getMemberName();
        this.title = community.getTitle();
        this.content = community.getContent();
        this.writeTime = community.getWriteTime();

    }
}


