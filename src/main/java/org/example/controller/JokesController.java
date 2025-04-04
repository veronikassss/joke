package org.example.controller;

import org.example.service.GetJokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JokesController {

    private final GetJokeService getJokeService;

    public JokesController(GetJokeService getJokeService) {
        this.getJokeService = getJokeService;
    }

    @GetMapping("/joke")
    public ResponseEntity<?> getJoke(@RequestHeader("X-User-Login") String login,
                                     @RequestHeader("X-User-Password") String password) {
        String joke = getJokeService.getJokeAndSaveOrFindUser(login, password);

        return ResponseEntity.ok(joke);
    }

    @GetMapping("/history")
    public ResponseEntity<?>  history(@RequestHeader("X-User-Login") String login,
                                      @RequestHeader("X-User-Password") String password) {
            List<Long> jokesIdOfUser = getJokeService.getUserHistory(login, password);

            return ResponseEntity.ok(jokesIdOfUser);
    }

}

