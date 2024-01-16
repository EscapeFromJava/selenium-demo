package com.example.seleniumdemo.repository;

import com.example.seleniumdemo.model.entity.Post;
/*import org.springframework.data.jpa.repository.JpaRepository;*/
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository /*extends JpaRepository<Post, Long>*/ {
    Post findByMessage(String message);
}
