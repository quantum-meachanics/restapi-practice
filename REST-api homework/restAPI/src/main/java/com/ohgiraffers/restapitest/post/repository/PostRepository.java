package com.ohgiraffers.restapitest.post.repository;

import com.ohgiraffers.restapitest.post.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Integer> {

}
