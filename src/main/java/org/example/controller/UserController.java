package org.example.controller;

import jakarta.validation.Valid;
import org.example.ErrorResponce;
import org.example.SuccessResponce;
import org.example.models.User;
import org.example.models.UserDTO;
import org.example.repository.DefaultUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController

public class UserController {

    private DefaultUserRepository defaultUserRepository;

    public UserController(DefaultUserRepository defaultUserRepository) {
        this.defaultUserRepository = defaultUserRepository;
    }

    @PostMapping("api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        String login = userDTO.getLogin();

        if (defaultUserRepository.existsByLogin(login)) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponce("Login is already exists", 409));
        }

        try {
            User newUser = new User(userDTO.getLogin(), LocalDateTime.now());
            defaultUserRepository.saveUser(newUser);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponce("User created", newUser.getLogin(), newUser.getRegisteredAt()));
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponce(e.getMessage(), 400));
        }
    }
}
