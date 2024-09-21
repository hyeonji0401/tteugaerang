package com.example.tteugaerang.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @NotEmpty(message ="이메일은 필수항목입니다")
    @Email
    private String email;
    @NotEmpty(message ="비밀번호는 필수항목입니다")
    private String password;
}
