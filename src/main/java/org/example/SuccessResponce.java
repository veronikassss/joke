package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SuccessResponce {
    private String status;
    private String login;
    private LocalDateTime registerAt;
}
