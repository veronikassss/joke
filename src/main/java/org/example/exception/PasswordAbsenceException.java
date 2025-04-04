package org.example.exception;

public class PasswordAbsenceException extends RuntimeException {
    public PasswordAbsenceException(String message) {
        super(message);
    }
}
