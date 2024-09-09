package com.ohgiraffers.homeworkporoject.service;

import com.ohgiraffers.homeworkporoject.domain.dto.CommentDTO;
import com.ohgiraffers.homeworkporoject.domain.entity.Comment;
import com.ohgiraffers.homeworkporoject.domain.entity.Post;
import com.ohgiraffers.homeworkporoject.repository.CommentRepository;
import com.ohgiraffers.homeworkporoject.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {


    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Comment comment = Comment.builder()
                    .id(commentDTO.getId())
                    .text(commentDTO.getText())
                    .post(post.get())
                    .build();
            Comment savedComment = commentRepository.save(comment);
            return convertToDTO(savedComment);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    public List<CommentDTO> getAllCommentsForPost(Long postId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPost().getPotsId().equals(postId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommentDTO> getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentDTO convertToDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .postId(comment.getPost().getPotsId())
                .build();
    }
}
