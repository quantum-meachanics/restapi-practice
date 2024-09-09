package com.ohgiraffers.homeworkporoject.service;

import com.ohgiraffers.homeworkporoject.domain.dto.PostDTO;
import com.ohgiraffers.homeworkporoject.domain.entity.Post;
import com.ohgiraffers.homeworkporoject.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public PostDTO createPost(PostDTO postDTO) {
        Post post = Post.builder()
                .potsId(postDTO.getId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .build();
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PostDTO> getPostById(Long id) {
        return postRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private PostDTO convertToDTO(Post post) {
        return PostDTO.builder()
                .id(post.getPotsId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}