package org.example.models;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class History {
    private String userLogin;
    private Set<Long> viewsJokesId = new HashSet<>();

    public void addViewJokeId(Long id) {
        viewsJokesId.add(id);

    }
}
