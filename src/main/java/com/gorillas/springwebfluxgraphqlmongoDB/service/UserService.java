package com.gorillas.springwebfluxgraphqlmongoDB.service;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.User;
import org.json.simple.JSONObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
    Mono<User> getByUserName(String userName);

    Flux<User> getAll();

    void createUser(JSONObject user);

    Mono<Long> userCount();

    Mono<String> getJwtToken(String userName, String password);
}
