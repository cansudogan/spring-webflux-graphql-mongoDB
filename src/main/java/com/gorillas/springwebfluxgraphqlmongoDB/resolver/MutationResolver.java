package com.gorillas.springwebfluxgraphqlmongoDB.resolver;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.UserDto;
import com.gorillas.springwebfluxgraphqlmongoDB.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;

@DgsComponent
public class MutationResolver {
    private final UserService userService;

    public MutationResolver(UserService userService) {
        this.userService = userService;
    }

    @DgsData(parentType = "Mutation", field = "authenticateUser")
    public String authenticateUser(UserDto userDto) {
        return userService.getJwtToken(userDto);
    }
}
