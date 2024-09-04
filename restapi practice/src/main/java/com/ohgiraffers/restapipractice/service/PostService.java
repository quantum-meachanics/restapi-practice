package com.ohgiraffers.restapipractice.service;

import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public PostDto createPost(PostDto newPost) {

        Post post = Post.builder()
                .title(newPost.getTitle())
                .content(newPost.getContent()).build();

        Post savedPost =  postRepository.save(post);

        return new PostDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }
}
