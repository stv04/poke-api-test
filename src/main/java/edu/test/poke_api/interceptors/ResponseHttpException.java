package edu.test.poke_api.interceptors;

import org.springframework.http.HttpStatus;

public class ResponseHttpException extends RuntimeException {
    HttpStatus status;
    public ResponseHttpException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
