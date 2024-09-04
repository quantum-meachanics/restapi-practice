package com.ohgiraffers.restapipractice.service;


import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post updatePost(Long id, Post modifyInfo) {
        Post update = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + id));

        update.setTitle(modifyInfo.getTitle());
        update.setContent(modifyInfo.getContent());
        return postRepository.save(update);
    }
}
