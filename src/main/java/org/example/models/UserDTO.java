package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 20, message = "Имя должно содержать от 3 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Допустимы только символы a-z и 0-9")
    private String login;
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 1, max = 20, message = "Пароль должен содержать от 1 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Допустимы только символы a-z и 0-9")
    private String password;
}
