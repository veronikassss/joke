package org.example.controller;

import jakarta.validation.Valid;
import org.example.ErrorResponce;
import org.example.SuccessResponce;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserServiceException;
import org.example.models.User;
import org.example.models.UserDTO;
import org.example.repository.DefaultUserRepository;
import org.example.service.GetRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController

public class UserController {

    private final GetRegisterService getRegisterService;

    public UserController(GetRegisterService getRegisterService) {
        this.getRegisterService = getRegisterService;
    }

    @PostMapping("api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        try {
            getRegisterService.registerUserOrReject(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponce(
                            "User created",
                            userDTO.getLogin(),
                            LocalDateTime.now()
                    ));

        } catch (UserAlreadyExistsException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponce("Login is already exists", 409));

        } catch (UserServiceException | IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponce(e.getMessage(), 400));
        }
    }
}
