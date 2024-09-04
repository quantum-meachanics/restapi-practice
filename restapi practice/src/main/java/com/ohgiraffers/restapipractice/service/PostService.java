package com.ohgiraffers.restapipractice.service;

import com.ohgiraffers.restapipractice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository postRepository){
        this.repo = postRepository;
    }

    public boolean deletePost(long postNo) {
        try {
            if (repo.existsById(postNo)) {
                repo.deleteById(postNo);
                return true; // 게시글 삭제 성공
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
}

