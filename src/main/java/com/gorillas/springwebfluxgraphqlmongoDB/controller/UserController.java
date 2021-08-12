package com.gorillas.springwebfluxgraphqlmongoDB.controller;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.User;
import com.gorillas.springwebfluxgraphqlmongoDB.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userName}")
    public Mono<User> getById(@PathVariable String userName) {
        return userRepository.findByUserName(userName);
    }

    @GetMapping
    public Flux<User> getAll() {
        return userRepository.findAll();
    }
}
