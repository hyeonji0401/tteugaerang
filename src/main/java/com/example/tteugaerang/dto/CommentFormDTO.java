package com.example.tteugaerang.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDTO {
    @NotEmpty
    String content;
}
