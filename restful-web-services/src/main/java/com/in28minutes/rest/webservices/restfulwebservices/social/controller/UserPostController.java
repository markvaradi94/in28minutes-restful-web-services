package com.in28minutes.rest.webservices.restfulwebservices.social.controller;

import com.in28minutes.rest.webservices.restfulwebservices.social.model.Post;
import com.in28minutes.rest.webservices.restfulwebservices.social.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(path = "users/{userId}/posts", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserPostController {
    private final PostService service;

//    @GetMapping
//    CollectionModel<Post> getAll(@PathVariable long userId) {
//        return CollectionModel.of(service.findAllPostsForUser(userId));
//    }

    @GetMapping
    List<Post> getAll(@PathVariable long userId) {
        return service.findAllPostsForUser(userId);
    }

    @GetMapping("{postId}")
    EntityModel<Post> getById(@PathVariable long userId, @PathVariable long postId) {
        return EntityModel.of(service.findPostForUser(userId, postId))
                .add(linkTo(methodOn(this.getClass()).getAll(userId)).withRel("all-user-posts"));
    }

    @PostMapping
    EntityModel<ResponseEntity<Post>> add(@PathVariable long userId, @RequestBody Post post) {
        Post savedPost = service.addPost(userId, post);
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        ResponseEntity<Post> responseEntity = ResponseEntity.created(location).body(savedPost);

        return EntityModel.of(responseEntity)
                .add(linkTo(methodOn(this.getClass()).getAll(userId)).withRel("all-user-posts"))
                .add(linkTo(methodOn(this.getClass()).getById(userId, savedPost.getId())).withRel("get-post"));
    }

    @DeleteMapping("{postId}")
    EntityModel<Post> delete(@PathVariable long userId, @PathVariable long postId) {
        return EntityModel.of(service.deletePost(userId, postId))
                .add(linkTo(methodOn(this.getClass()).getAll(userId)).withRel("all-user-posts"));
    }
}
