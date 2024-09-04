package com.ohgiraffers.restapipractice.domain.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "tbl_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;

    @Builder
    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
