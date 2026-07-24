package com.gridweaver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.dto.UserDTO;
import com.gridweaver.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(
            @Valid @RequestBody UserDTO userDTO) {

        UserDTO savedUser = userService.registerUser(userDTO);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}