package com.in28minutes.rest.webservices.restfulwebservices.social.repository;

import com.in28minutes.rest.webservices.restfulwebservices.social.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(long userId);

    Optional<Post> findByIdAndUserId(long id, long userId);
}
