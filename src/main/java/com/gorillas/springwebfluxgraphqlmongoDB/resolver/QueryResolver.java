package com.gorillas.springwebfluxgraphqlmongoDB.resolver;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.User;
import com.gorillas.springwebfluxgraphqlmongoDB.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DgsComponent
public class QueryResolver {
    private final UserService userService;

    public QueryResolver(UserService userService) {
        this.userService = userService;
    }

    @DgsData(parentType = "Query", field = "getAll")
    public Flux<User> getAll() {
        return userService.getAll();
    }

    @DgsData(parentType = "Query", field = "getByUserName")
    public Mono<User> getByUserName(String userName) {
        return userService.getByUserName(userName);
    }

}
