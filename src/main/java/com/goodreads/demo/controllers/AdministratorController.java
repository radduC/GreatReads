package com.goodreads.demo.controllers;

import com.goodreads.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdministratorController {

    private final UserService userService;

    @PostMapping("/lock")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<String> lockAccount(@RequestParam String email) {
        userService.setAccountNonLockValue(email, false);
        return ResponseEntity.ok("Account succesfully locked: " + email);
    }

    @PostMapping("/unlock")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<String> unlockAccount(@RequestParam String email) {
        userService.setAccountNonLockValue(email, true);
        return ResponseEntity.ok("Account succesfully unlocked: " + email);
    }
}
