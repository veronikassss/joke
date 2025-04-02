package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository implements UserRepositoryI {
    private final ConcurrentHashMap<String, User> usersMap = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Override
    public void saveUser(User user) {
        if (user == null) {
            logger.warn("user is null");
            throw new NullPointerException("user is null");
        }

        String userLogin = user.getLogin();
        if (userLogin == null || userLogin.isEmpty()) {
            logger.warn("userLogin is null or empty");
            throw new IllegalArgumentException("user login cannot be null");
        }

        usersMap.putIfAbsent(userLogin, user);
        logger.info("save user " + userLogin);
    }

    @Override
    public User findOrCreate(String login) {
        User oldUser = usersMap.get(login);
        if ( oldUser != null ) {
            logger.info("user " + login + " was in system already");
            return oldUser;
        }

        User newUser = new User(login, LocalDateTime.now());
        usersMap.put(login, newUser);
        logger.info("user " + login + " was created");
        return newUser;
    }

    @Override
    public boolean existsByLogin(String username) {
        return usersMap.containsKey(username);
    }

    public Set<Long> history(String login) {
        User user = usersMap.get(login);

        return (user != null) ? user.getViewsJokesId() : Collections.emptySet(); // возвращает пустой список, если user == null
    }

}
