package org.example.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Getter
public class User {
    @Setter
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 20, message = "Имя должно содержать от 3 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Допустимы только символы a-z и 0-9")
    private String login;
    private LocalDateTime registeredAt;
    private Set<Long> viewsJokesId = new HashSet<>();

    public User(String login, LocalDateTime registeredAt) {
        this.login = login;
        this.registeredAt = registeredAt;
    }

    public User(String login, LocalDateTime registeredAt, Set<Long> viewsJokesId) {
        this.login = login;
        this.registeredAt = registeredAt;
        this.viewsJokesId = viewsJokesId;
    }

    public void addViewJokeId(Long id) {
        viewsJokesId.add(id);

    }


}
