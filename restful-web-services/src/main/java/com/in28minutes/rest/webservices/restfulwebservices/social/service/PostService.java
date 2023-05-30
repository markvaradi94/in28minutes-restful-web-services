package com.in28minutes.rest.webservices.restfulwebservices.social.service;

import com.in28minutes.rest.webservices.restfulwebservices.social.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.social.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.social.model.Post;
import com.in28minutes.rest.webservices.restfulwebservices.social.model.User;
import com.in28minutes.rest.webservices.restfulwebservices.social.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo repo;
    private final UserService userService;

    public List<Post> findAllPostsForUser(long userId) {
        User user = getUserOrThrow(userId);
        return repo.findAllByUserId(user.getId());
    }

    public Post findPostForUser(long userId, long postId) {
        User user = getUserOrThrow(userId);
        return repo.findByIdAndUserId(postId, userId)
                .orElseThrow(() -> new PostNotFoundException("Could not find post with id %s for user with id %s".formatted(postId, user.getId())));
    }

    public Post addPost(long userId, Post post) {
        User user = getUserOrThrow(userId);
        return repo.save(post.withUser(user));
    }

    public Post deletePost(long userId, long postId) {
        User user = getUserOrThrow(userId);
        Post postToDelete = repo.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new PostNotFoundException("Could not find post with id %s for user with id %s".formatted(postId, user.getId())));
        repo.delete(postToDelete);
        return postToDelete;
    }

    private User getUserOrThrow(long userId) {
        return userService.findUser(userId)
                .orElseThrow(() -> new UserNotFoundException("Could not find user with id %s".formatted(userId)));
    }
}
