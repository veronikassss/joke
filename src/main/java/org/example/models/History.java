package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class History {
    private Set<Long> viewsJokesId = new HashSet<>();

    public void addViewJokeId(Long id) {
        viewsJokesId.add(id);

    }
}
