package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserServiceException;
import org.example.models.User;
import org.example.models.UserDTO;
import org.example.repository.DefaultUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetRegisterService {

    private DefaultUserRepository defaultUserRepository;
    private final Logger logger = LoggerFactory.getLogger(GetRegisterService.class);

    public GetRegisterService(DefaultUserRepository defaultUserRepository) {
        this.defaultUserRepository = defaultUserRepository;
    }

    public void registerUserOrReject(UserDTO userDTO) {
            String userDTOlogin = userDTO.getLogin();

            if (defaultUserRepository.existsByLogin(userDTOlogin)) {
                throw new UserAlreadyExistsException("This login already exists, please choose another one");
            }

        try {
            User newUser = new User(userDTO.getLogin(),userDTO.getPassword(), LocalDateTime.now());
            defaultUserRepository.saveUser(newUser);
            logger.info("user was saved in method registerUserOrReject");
        } catch (IllegalArgumentException e) {
            throw new UserServiceException("Invalid user data: " + e.getMessage());
        }
    }
}
