package com.gridweaver.service;

import com.gridweaver.dto.LoginRequest;
import com.gridweaver.dto.UserDTO;
import com.gridweaver.dto.LoginResponse;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);

    LoginResponse loginUser(LoginRequest loginRequest);

}