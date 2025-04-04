package org.example.exception;

public class LoginAbsenceException extends RuntimeException {
    public LoginAbsenceException(String message) {
        super(message);
    }
}
