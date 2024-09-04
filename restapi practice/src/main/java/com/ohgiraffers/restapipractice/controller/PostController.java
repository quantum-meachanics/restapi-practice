package com.ohgiraffers.restapipractice.controller;


import com.ohgiraffers.restapipractice.ResponseMessage;
import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @Operation(summary = "전체 게시글 조회", description = "작성된 모든 게시글 조회")
    @GetMapping("/posts")
    public ResponseEntity<ResponseMessage> findAllPosts() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("posts", service.findAllPosts());

        ResponseMessage responseMessage = new ResponseMessage(
                200,
                " 게시글 조회 성공",
                responseMap
        );

        return new ResponseEntity<>(responseMessage, header, HttpStatus.OK);
    }

    @Operation(summary = "아이디로 게시글 조회", description = "아이디를 입력하여 게시글 조회")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResponseMessage> findPostById(@PathVariable long postId) {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(
                new MediaType(
                        "application",
                        "json",
                        Charset.forName("UTF-8")
                )
        );

        Post foundPost = service.findPostById(postId);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("post", foundPost);

        return ResponseEntity.ok()
                .headers(header)
                .body(new ResponseMessage(200, "게시글 아이디로 조회 성공", responseMap));
    }

    @PostMapping("/create")
    public ResponseEntity<?> regist(@RequestBody PostDto newPost) {

        // 유저추가
        PostDto createdPost = service.createPost(newPost);

        return ResponseEntity
                .created(URI.create("/entity/posts/" + createdPost.getId()))
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable long postId) {
        Map<String, Object> responseMap = new HashMap<>();

        boolean isDeleted = service.deletePost(postId);
        if (isDeleted) {
            String msg = "게시글 삭제에 성공하였습니다.";
            responseMap.put("result", msg);
        } else {
            String msg = "게시글 삭제에 실패하였습니다.";
            responseMap.put("result", msg);
        }

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(204, "게시글 삭제 성공", responseMap));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPost(@PathVariable Long id, @RequestBody Post modifyInfo) {
        Post updatedPost = service.updatePost(id, modifyInfo);
        return ResponseEntity
                .created(URI.create("/posts/" + updatedPost.getId()))

                .build();
    }

}
