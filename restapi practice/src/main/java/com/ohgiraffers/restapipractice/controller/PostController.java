package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPost(@PathVariable Long id, @RequestBody Post modifyInfo) {
        Post updatedPost = postService.updatePost(id, modifyInfo);
        return ResponseEntity
                .created(URI.create("/posts/" + updatedPost.getId()))

                .build();
    }

}

