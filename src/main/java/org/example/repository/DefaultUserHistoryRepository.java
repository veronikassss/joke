package org.example.repository;

import org.example.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultUserHistoryRepository implements UserHistoryRepository {

    private final ConcurrentHashMap<String, List<Long>> historyMap = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(DefaultUserHistoryRepository.class);

    @Override
    public void save(String login, Long jokeId) {
        if (login == null || login.isEmpty()) {
            logger.error("login is null or empty to save history");
        }
        historyMap.computeIfAbsent(login, k -> new ArrayList<>()).add(jokeId);
    }

    @Override
    public List<Long> getJokesByUser(String login) {
        if (login == null || login.isEmpty()) {
            logger.error("login is null or empty to get jokes");
        }
        List<Long> listOdIds = historyMap.get(login);

        return listOdIds;
    }
}
