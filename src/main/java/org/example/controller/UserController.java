package org.example.controller;

import jakarta.validation.Valid;
import org.example.ErrorResponce;
import org.example.SuccessResponce;
import org.example.models.User;
import org.example.models.UserDTO;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController

public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        String login = userDTO.getLogin();

        if (userRepository.existsByLogin(login)) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponce("Login is already exists", 409));
        }

        try {
            User newUser = new User(userDTO.getLogin(), LocalDateTime.now());
            userRepository.saveUser(newUser);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponce("User created", newUser.getLogin(), newUser.getRegisteredAt()));
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponce(e.getMessage(), 400));
        }
    }
}
