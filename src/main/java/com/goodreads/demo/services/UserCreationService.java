package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.users.User;

public interface UserCreationService {
    User createUser(RegisterRequest registerRequest);
}
