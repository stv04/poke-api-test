package edu.test.poke_api.models;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionHttpModel {
    int status;
    String message;
    String path;
    LocalDateTime timestamp;

    public ExceptionHttpModel() {}

    public ExceptionHttpModel(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    
}
