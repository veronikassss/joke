package org.example.repository;

import org.example.models.Joke;

import java.util.List;

public interface UserHistoryRepository {

    void save(String login, Long jokeId);
    List<Long> getJokesByUser(String login);
}
