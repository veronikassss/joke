package org.example.controller;

import org.example.exception.JokeServiceException;
import org.example.models.Joke;
import org.example.models.User;
import org.example.repository.DefaultUserRepository;
import org.example.service.JokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class JokesController {

    private JokeService jokeService;
    private DefaultUserRepository defaultUserRepository;

    public JokesController(JokeService jokeService, DefaultUserRepository defaultUserRepository) {
        this.jokeService = jokeService;
        this.defaultUserRepository = defaultUserRepository;
    }

//    @GetMapping("/joke")
//    public ResponseEntity<?> getJoke(@RequestHeader("X-User-Login") String login) {
//        try {
//            User user = defaultUserRepository.findOrCreate(login);
//            Joke joke = jokeService.getJoke();
//            user.addViewJokeId(joke.getId());
//
//            return ResponseEntity.ok(joke);
//
//        } catch (JokeServiceException e) {
//            throw e;
//        }
//    }

    @GetMapping("/history")
    public ResponseEntity<?>  history(@RequestHeader("X-User-Login") String login) {
            Set<Long> jokesIdOfUser = defaultUserRepository.history(login);

            return ResponseEntity.ok(jokesIdOfUser);
    }

}

