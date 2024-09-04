package com.ohgiraffers.restapipractice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
}
