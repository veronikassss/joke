package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Joke {
    private final Long id;
    private final String text;

    public Joke(@JsonProperty("id") Long id,
                @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }
}