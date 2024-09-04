package com.ohgiraffers.restapipractice.controller;

import com.ohgiraffers.restapipractice.ResponseMessage;
import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/quantum")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @Operation(summary = "전체 게시글 조회", description = "작성된 모든 게시글 조회")
    @GetMapping("/posts")
    public ResponseEntity<ResponseMessage> findAllPosts() {

        ResponseMessage responseMessage = new ResponseMessage(
                200,
                "전체 게시글 조회 성공",
                service.findAllPosts() // 모든 게시글을 Dto리스트 형태로 리턴하는 메소드
        );

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        200,
                        "전체 게시글 조회 성공",
                        responseMessage)
                );
    }

    @Operation(summary = "아이디로 게시글 조회", description = "아이디를 입력하여 게시글 조회")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResponseMessage> findPostById(@PathVariable long postId) {

        ResponseMessage responseMessage = new ResponseMessage(
                200,
                "아이디로 게시글 조회 성공",
                service.findPostById(postId) // 아이디에 해당하는 postDto 반환하는 메소드
        );

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        200,
                        "아이디로 게시글 조회 성공"
                        , responseMessage)
                );
    }

    @PostMapping("/posts")
    public ResponseEntity<?> regist(@RequestBody PostDto postDto) {

        // 유저추가
        PostDto createdPost = service.createPost(postDto);

        return ResponseEntity.ok()
//                .created(
//                        URI.create("/entity/posts/" + createdPost.getId()))
                .body(new ResponseMessage(
                        201,
                        "게시글 등록 성공",
                        createdPost)
                );
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> modifyPost(@PathVariable Long id, @RequestBody PostDto modifyInfo) {

        PostDto updatedPost = service.updatePost(id, modifyInfo);

        return ResponseEntity.ok()
                .body(new ResponseMessage(
                        201,
                        "게시글 수정 완료",
                        updatedPost)
                );
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
                .body(new ResponseMessage(
                        204,
                        "게시글 삭제 성공",
                        responseMap)
                );
    }
}

