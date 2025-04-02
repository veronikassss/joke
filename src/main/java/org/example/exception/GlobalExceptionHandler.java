package org.example.exception;

import org.example.ErrorResponce;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Обработка отсутствия обязательного заголовка
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponce> handleMissingHeader(MissingRequestHeaderException ex) {
        String message = "Отсутствует обязательный заголовок: " + ex.getHeaderName();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponce(message, 401));
    }

    // Обработка ошибок валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponce> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Ошибка валидации");

        return ResponseEntity.badRequest()
                .body(new ErrorResponce(errorMessage, 400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponce> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponce("Внутренняя ошибка сервера", 500));
    }
}