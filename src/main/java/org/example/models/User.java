package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
public class User {
    @Setter
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 20, message = "Имя должно содержать от 3 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Допустимы только символы a-z и 0-9")
    private String login;
    private final LocalDateTime registeredAt;

    public User(String login, LocalDateTime registeredAt) {
        this.login = login;
        this.registeredAt = registeredAt;
    }
}
