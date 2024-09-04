package com.ohgiraffers.restapipractice.service;

import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository postRepository) {
        this.repo = postRepository;
    }

    public List<Post> findAllPosts() {
        return new ArrayList<>(repo.findAll());
    }

    public Post findPostById(long postId) {
        return repo.findById(postId);
    }
}
