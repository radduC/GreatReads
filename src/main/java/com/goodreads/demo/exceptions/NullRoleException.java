package com.goodreads.demo.exceptions;

public class NullRoleException extends RuntimeException {
    public NullRoleException(String message) {
        super(message);
    }
}
