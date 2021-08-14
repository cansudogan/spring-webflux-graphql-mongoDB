package com.gorillas.springwebfluxgraphqlmongoDB.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends Exception {

    private final HttpStatus httpStatus;

    public HttpException(String message, HttpStatus httpStatus) {

        super(message);

        this.httpStatus = httpStatus;

    }
}
