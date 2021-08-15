package com.gorillas.springwebfluxgraphqlmongoDB.resolver;

import com.gorillas.springwebfluxgraphqlmongoDB.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import reactor.core.publisher.Mono;

@DgsComponent
public class MutationResolver {
    private final UserService userService;

    public MutationResolver(UserService userService) {
        this.userService = userService;
    }

    @DgsData(parentType = "Mutation", field = "authenticateUser")
    public Mono<String> authenticateUser(String userName, String password) {
        return userService.getJwtToken(userName, password);
    }
}
