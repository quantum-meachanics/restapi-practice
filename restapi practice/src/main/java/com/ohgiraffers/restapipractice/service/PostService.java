package com.ohgiraffers.restapipractice.service;


import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository postRepository) {
        this.repo = postRepository;
    }

    public List<PostDto> findAllPosts() {
        return repo.findAll().stream().map(entity ->
                        PostDto.builder()
                                .id(entity.getId())
                                .title(entity.getTitle())
                                .content(entity.getContent())
                                .build())
                .collect(Collectors.toList()
                );
    }

    public PostDto findPostById(long postId) {

        Post postEntity = repo.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + postId));

        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .build();
    }

    public PostDto createPost(PostDto newPost) {

        Post post = Post.builder()
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .build();

        Post savedPost = repo.save(post);

        return new PostDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }

    public PostDto updatePost(Long id, PostDto modifyInfo) {

        Post postEntity = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + id));

        Post updatePost = postEntity.builder()
                .title(modifyInfo.getTitle())
                .content(modifyInfo.getContent())
                .build();

        repo.save(updatePost);

        return new PostDto(updatePost.getId(), updatePost.getTitle(), updatePost.getContent());
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
