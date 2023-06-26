package ru.job4j.url.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointerExceptionHandle(Exception exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new HashMap<>() {{
                put("message", exc.getMessage());
                put("details", exc.getClass());
                }});
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementExceptionHandle(Exception exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HashMap<>() {{
                    put("message", exc.getMessage());
                    put("details", exc.getClass());
                }});
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandle(
            MethodArgumentNotValidException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exc.getFieldErrors().stream()
                        .map(field -> Map.of(field.getField(),
                                String.format("%s. Actual value: %s",
                                        field.getDefaultMessage(),
                                        field.getRejectedValue())))
                        .collect(Collectors.toList()));
    }
}
