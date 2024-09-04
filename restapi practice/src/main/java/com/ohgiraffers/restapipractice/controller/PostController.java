package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping("/posts")
public class PostController {

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
