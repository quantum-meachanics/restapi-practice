package com.ohgiraffers.restapitest.comment.service;

import com.ohgiraffers.restapitest.comment.domain.dto.CommentDTO;
import com.ohgiraffers.restapitest.comment.domain.dto.CreateCommentDTO;
import com.ohgiraffers.restapitest.comment.domain.entity.CommentEntity;
import com.ohgiraffers.restapitest.comment.repository.CommentRepository;
import com.ohgiraffers.restapitest.post.domain.entity.PostEntity;
import com.ohgiraffers.restapitest.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 특정 게시글의 댓글 조회
    public List<CommentEntity> findCommentsByPost(int postId) {
        log.info("전체 댓글 조회 중...");
        return postRepository.findById(postId).get().getComment();
    }

    // 전체 댓글 조회
    public List<CommentEntity> findAllComments() {
        return commentRepository.findAll();
    }

    // 댓글 등록
    public CommentEntity registComment(CreateCommentDTO commentDTO) {

        PostEntity foundPost= postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. postId: " + commentDTO.getPostId()));

        CommentEntity commentEntity = CommentEntity.builder()
                .postId(foundPost)
                .content(commentDTO.getContent())
                .createDate(LocalDateTime.now())
                .build();

        return commentRepository.save(commentEntity);
    }

    // 댓글 수정
    public CommentEntity updateComment(int commentId, CommentDTO modifyInfo) {

        CommentEntity comment= commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. comment: " + commentId));

        comment = comment.toBuilder()
                .postId(modifyInfo.getPostId())
                .content(modifyInfo.getContent())
                .build();

        return commentRepository.save(comment);
    }


    // 댓글 삭제
    public void deleteComment(int commentId) {
            commentRepository.deleteById(commentId);
        }
}

