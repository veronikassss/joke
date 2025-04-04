package org.example.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class History {
    private String userLogin;
    private List<Long> viewsJokesId = new ArrayList<>();;

    public void addViewJokeId(Long id) {
        viewsJokesId.add(id);

    }
}
