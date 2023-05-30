package com.in28minutes.rest.webservices.restfulwebservices.social.service;

import com.in28minutes.rest.webservices.restfulwebservices.social.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.social.model.User;
import com.in28minutes.rest.webservices.restfulwebservices.social.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;


    public List<User> findAllUsers() {
        return repo.findAll();
    }

    public Optional<User> findUser(long id) {
        return repo.findById(id);
    }

    public User addUser(User newUser) {
        return repo.save(newUser);
    }

    public User delete(long id) {
        User userToDelete = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("Could not find user with id %s", id)));
        repo.delete(userToDelete);
        return userToDelete;
    }
}
