package com.ohgiraffers.homeworkporoject.repository;

import com.ohgiraffers.homeworkporoject.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
