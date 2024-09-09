package com.ohgiraffers.restapitest.comment.repository;

import com.ohgiraffers.restapitest.comment.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

}
