package com.gorillas.springwebfluxgraphqlmongoDB.service.impl;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.User;
import com.gorillas.springwebfluxgraphqlmongoDB.entity.UserDto;
import com.gorillas.springwebfluxgraphqlmongoDB.exception.HttpException;
import com.gorillas.springwebfluxgraphqlmongoDB.repository.UserRepository;
import com.gorillas.springwebfluxgraphqlmongoDB.security.JwtUtils;
import com.gorillas.springwebfluxgraphqlmongoDB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .switchIfEmpty(Mono.error(new HttpException("User doesn't exist!", HttpStatus.NOT_FOUND)));
    }

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll()
                .switchIfEmpty(Flux.error(new HttpException("There is no user!", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void createUser(JSONObject user) {
        User userObj = User.builder().firstName((String) user.get("firstName"))
                .lastName((String) user.get("lastName"))
                .userName((String) user.get("userName"))
                .password((String) user.get("password"))
                .email((String) user.get("email"))
                .build();
        userRepository.save(userObj).subscribe(System.out::println);
    }

    @Override
    public Mono<Long> userCount() {
        return userRepository.count();
    }

    @Override
    public String getJwtToken(UserDto userDto) {
        return jwtUtils.generateJwtToken(userDto);
    }
}
