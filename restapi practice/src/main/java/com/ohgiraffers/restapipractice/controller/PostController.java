package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quantum")
public class PostController {

    private final PostService postService;

    private List<PostDto> posts;

    public PostController(PostService postService) {
        this.postService = postService;

        posts = new ArrayList<>();

        posts.add(new PostDto(1,"제목1","내용1"));
        posts.add(new PostDto(2,"제목2", "내용2"));
        posts.add(new PostDto(3, "제목3","내용3"));
    }

    @PostMapping("/create")
    public ResponseEntity<?> regist(@RequestBody PostDto newPost) {

        long lastPostNum = posts.get(posts.size() - 1).getId();

        newPost.setId(lastPostNum + 1);

        // 유저추가
        posts.add(postService.createPost(newPost));

        return ResponseEntity
                .created(URI.create("/entity/posts/" + posts.get(posts.size() - 1).getId()))
                .build();
    }

}

