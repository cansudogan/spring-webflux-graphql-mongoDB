package com.gorillas.springwebfluxgraphqlmongoDB.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;


}
