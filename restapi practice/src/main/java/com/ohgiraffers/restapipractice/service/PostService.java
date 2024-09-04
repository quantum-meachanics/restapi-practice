package com.ohgiraffers.restapipractice.service;


import com.ohgiraffers.restapipractice.domain.dto.PostDto;
import com.ohgiraffers.restapipractice.domain.entity.Post;
import com.ohgiraffers.restapipractice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository postRepository) {
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

    public List<Post> findAllPosts() {
        return new ArrayList<>(repo.findAll());
    }

    public Post findPostById(long postId) {
        return repo.findById(postId);
    }

    public PostDto createPost(PostDto newPost) {

        Post post = Post.builder()
                .title(newPost.getTitle())
                .content(newPost.getContent()).build();

        Post savedPost = repo.save(post);

        return new PostDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }

    public Post updatePost(Long id, Post modifyInfo) {
        Post update = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + id));

        update.setTitle(modifyInfo.getTitle());
        update.setContent(modifyInfo.getContent());
        return repo.save(update);
    }
}
