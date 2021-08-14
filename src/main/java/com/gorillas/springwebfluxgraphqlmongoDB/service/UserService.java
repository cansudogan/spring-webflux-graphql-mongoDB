package com.gorillas.springwebfluxgraphqlmongoDB.service;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.User;
import com.gorillas.springwebfluxgraphqlmongoDB.entity.UserDto;
import org.json.simple.JSONObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
    Mono<User> getByUserName(String userName);

    Flux<User> getAll();

    void createUser(JSONObject user);

    Mono<Long> userCount();

    String getJwtToken(UserDto userDto);
}
