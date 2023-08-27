package com.goodreads.demo.aspects;

import com.goodreads.demo.exceptions.IdenticalEmailException;
import com.goodreads.demo.exceptions.UserAlreadyExistsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        Map<String, String> body = new HashMap<>();
        String error = e.getMessage();
        body.put("error", error);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        Map<String, String> body = new HashMap<>();
        String error = e.getMessage();
        body.put("error", error);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        Map<String, String> body = new HashMap<>();
        String error = e.getMessage();
        body.put("error", error);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(IdenticalEmailException.class)
    public ResponseEntity<?> handleIdenticalEmailException(IdenticalEmailException e) {
        Map<String, String> body = new HashMap<>();
        String error = e.getMessage();
        body.put("error", error);
        return ResponseEntity.badRequest().body(body);
    }
}
