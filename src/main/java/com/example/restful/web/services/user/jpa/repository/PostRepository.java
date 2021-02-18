package com.example.restful.web.services.user.jpa.repository;

import com.example.restful.web.services.user.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
