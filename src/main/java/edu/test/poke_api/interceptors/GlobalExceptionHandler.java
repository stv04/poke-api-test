package edu.test.poke_api.interceptors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.test.poke_api.models.ExceptionHttpModel;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResponseHttpException.class)
    public ResponseEntity<ExceptionHttpModel> handleExeption(ResponseHttpException ex, HttpServletRequest request) {
        ExceptionHttpModel res = new ExceptionHttpModel(ex.status, ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(res, ex.status);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionHttpModel> handleExeption(Exception ex, HttpServletRequest request) {
        ExceptionHttpModel res = new ExceptionHttpModel(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
