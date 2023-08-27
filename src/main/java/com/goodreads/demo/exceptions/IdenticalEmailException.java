package com.goodreads.demo.exceptions;

public class IdenticalEmailException extends RuntimeException {
    public IdenticalEmailException(String message) {
        super(message);
    }
}
