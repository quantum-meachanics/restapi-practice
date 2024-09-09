package com.ohgiraffers.homeworkporoject.repository;

import com.ohgiraffers.homeworkporoject.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
