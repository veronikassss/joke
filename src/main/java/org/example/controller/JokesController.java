package org.example.controller;

import jakarta.validation.Valid;
import org.example.ErrorResponce;
import org.example.exception.JokeServiceException;
import org.example.SuccessResponce;
import org.example.models.Joke;
import org.example.models.User;
import org.example.models.UserDTO;
import org.example.repository.UserRepository;
import org.example.service.JokeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class JokesController {

    private JokeService jokeService;
    private UserRepository userRepository;

    public JokesController(JokeService jokeService, UserRepository userRepository) {
        this.jokeService = jokeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/joke")
    public ResponseEntity<?> getJoke(@RequestHeader("X-User-Login") String login) {
        try {
            User user = userRepository.findOrCreate(login);
            Joke joke = jokeService.getJoke();
            user.addViewJokeId(joke.getId());

            return ResponseEntity.ok(joke);

        } catch (JokeServiceException e) {
            throw e;
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?>  history(@RequestHeader("X-User-Login") String login) {
            Set<Long> jokesIdOfUser = userRepository.history(login);

            return ResponseEntity.ok(jokesIdOfUser);
    }

}

