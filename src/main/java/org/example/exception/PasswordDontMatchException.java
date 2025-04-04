package org.example.exception;

public class PasswordDontMatchException extends RuntimeException {
    public PasswordDontMatchException(String message) {
        super(message);
    }
}
