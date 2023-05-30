package com.in28minutes.rest.webservices.restfulwebservices.social.repository;

import com.in28minutes.rest.webservices.restfulwebservices.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
