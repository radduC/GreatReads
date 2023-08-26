package com.goodreads.demo.controllers;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.dto.UserDTO;
import com.goodreads.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public UserDTO register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }
}
