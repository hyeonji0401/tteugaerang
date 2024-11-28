package com.example.tteugaerang.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer" , nullable=false)
    private Member writer;

    @ManyToOne
    @JoinColumn(name="community", nullable = false)
    private Community community;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime writeTime;

    @Column
    private LocalDateTime updateTime;

}
