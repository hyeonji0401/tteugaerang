package com.example.tteugaerang.dto;
import com.example.tteugaerang.domain.Community;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommunityDTO {
    @NotEmpty
    private String writer;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private LocalDateTime writeTime;

    public CommunityDTO(Community community){
        this.writer = community.getWriter().getMemberName();
        this.title = community.getTitle();
        this.content = community.getContent();
        this.writeTime = community.getWriteTime();

    }
}


