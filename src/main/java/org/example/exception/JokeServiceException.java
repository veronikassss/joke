package org.example.exception;

public class JokeServiceException extends RuntimeException {
  public JokeServiceException(String message) {
    super(message);
  }

  public JokeServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}