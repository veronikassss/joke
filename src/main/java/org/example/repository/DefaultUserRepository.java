package org.example.repository;

import org.example.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultUserRepository implements UserRepository {
    private final ConcurrentHashMap<String, User> usersMap = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(DefaultUserRepository.class);

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
    public User find(String login) {
        User oldUser = usersMap.get(login);
        if (oldUser == null) {
            logger.warn("user not found");
            throw new IllegalArgumentException("user not found");
        }
            return oldUser;
    }

    @Override
    public boolean existsByLogin(String username) {
        return usersMap.containsKey(username);
    }
}
