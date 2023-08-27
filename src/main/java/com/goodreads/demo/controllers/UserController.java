package com.goodreads.demo.controllers;

import com.goodreads.demo.dto.EmailChangeRequest;
import com.goodreads.demo.dto.PasswordChangeRequest;
import com.goodreads.demo.dto.UserDTO;
import com.goodreads.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/change-password")
    public void changePassword(@RequestBody @Valid PasswordChangeRequest passwordChangeRequest) {
        
    }

    @PostMapping("/change-email")
    public UserDTO changeEmail(@RequestBody @Valid EmailChangeRequest emailChangeRequest, Principal principal) {
        var currentUser = principal.getName();
        return userService.changeEmail(currentUser, emailChangeRequest.newEmail());
    }
}
