package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.ResponseMessage;
import com.ohgiraffers.restapipractice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost (@PathVariable long postId){
        Map<String, Object> responseMap = new HashMap<>();

        boolean isDeleted = postService.deletePost(postId);
        if(isDeleted) {
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
}
