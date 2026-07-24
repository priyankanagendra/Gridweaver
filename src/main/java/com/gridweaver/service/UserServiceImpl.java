package com.gridweaver.service;

import org.springframework.stereotype.Service;

import com.gridweaver.dto.UserDTO;
import com.gridweaver.entity.User;
import com.gridweaver.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.gridweaver.dto.LoginRequest;
import com.gridweaver.dto.LoginResponse;
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public UserServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

this.userRepository = userRepository;
this.passwordEncoder = passwordEncoder;
this.jwtService = jwtService;
}

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        // For now, save the password as it is.
        // In the next step, we'll encrypt it using BCrypt.
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(user);

        UserDTO response = new UserDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        // Never return the password
        return response;
    }
    
    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password."));

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}