package org.example.service;

import org.example.exception.JokeServiceException;
import org.example.exception.LoginAbsenceException;
import org.example.exception.PasswordAbsenceException;
import org.example.exception.PasswordDontMatchException;
import org.example.models.Joke;
import org.example.models.User;
import org.example.repository.DefaultUserHistoryRepository;
import org.example.repository.DefaultUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GetJokeService {

    private final DefaultUserRepository defaultUserRepository;
    private final DefaultUserHistoryRepository defaultUserHistoryRepository;
    private final JokeService jokeService;
    private final Logger logger = LoggerFactory.getLogger(GetJokeService.class);

    @Autowired
    public GetJokeService(DefaultUserRepository defaultUserRepository, DefaultUserHistoryRepository defaultUserHistoryRepository, JokeService jokeService) {
        this.defaultUserRepository = defaultUserRepository;
        this.defaultUserHistoryRepository = defaultUserHistoryRepository;
        this.jokeService = jokeService;
    }

    public String getJokeAndSaveOrFindUser(String login, String password) {
        Joke joke = jokeService.getJoke();
        if (joke == null) {
            logger.warn("problem with getting joke");
            throw new JokeServiceException("problem with getting joke");
        }

        if (login == null || login.isEmpty()) {
            logger.warn("login is null or empty in the method getJokeAndSaveOrFindUser ");
            throw new LoginAbsenceException("Login required");
        }

        if (password == null || password.isEmpty()) {
            logger.warn("password is null or empty in the method getJokeAndSaveOrFindUser ");
            throw new PasswordAbsenceException("Password required");
        }

        User user = defaultUserRepository.find(login);

        if (!user.getPassword().equals(password)) {
            logger.warn("password don't match");
            throw new PasswordDontMatchException("password don't match");
        }

        if (user == null) {
            logger.warn("user not found. we will save a new one");
            user = new User(login,password, LocalDateTime.now());
            defaultUserRepository.saveUser(user);
            logger.info("saved user");
        }
        defaultUserHistoryRepository.save(login, joke.getId());

        return joke.getText();
    }

    public List<Long> getUserHistory(String login, String password) {
        if (login == null || login.isEmpty()) {
            logger.warn("login is null or empty in method getUserHistory");
            throw new IllegalArgumentException("user login cannot be null");
        }
        List<Long> userHistory = defaultUserHistoryRepository.getJokesByUser(login);

        return userHistory;
    }
}
