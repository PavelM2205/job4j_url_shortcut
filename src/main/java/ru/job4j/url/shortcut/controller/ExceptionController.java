package ru.job4j.url.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.NoSuchElementException;

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
}
