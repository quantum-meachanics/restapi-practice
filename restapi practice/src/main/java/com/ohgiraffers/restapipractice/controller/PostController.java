package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> regist(@RequestBody PostDto newPost) {

        // 유저추가
        PostDto createdPost = postService.createPost(newPost);

        return ResponseEntity
                .created(URI.create("/entity/posts/" + createdPost.getId()))
                .build();
    }

}

