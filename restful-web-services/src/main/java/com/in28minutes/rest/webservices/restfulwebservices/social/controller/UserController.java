package com.in28minutes.rest.webservices.restfulwebservices.social.controller;

import com.in28minutes.rest.webservices.restfulwebservices.social.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.social.model.User;
import com.in28minutes.rest.webservices.restfulwebservices.social.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(path = "users", produces = "application/json")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    List<User> getAll() {
        return service.findAllUsers();
    }

    @GetMapping("{id}")
    EntityModel<User> getById(@PathVariable long id) {
        return EntityModel.of(service.findUser(id)
                        .orElseThrow(() -> new UserNotFoundException(String.format("Could not find user with id %s", id))))
                .add(linkTo(methodOn(this.getClass()).getAll()).withRel("all-users"));
    }

    @PostMapping
    ResponseEntity<User> add(@Valid @RequestBody User newUser) {
        User savedUser = service.addUser(newUser);
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(savedUser);
    }

    @DeleteMapping("{id}")
    User delete(@PathVariable long id) {
        return service.delete(id);
    }
}
