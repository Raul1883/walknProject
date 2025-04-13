package com.night.backendWalkn.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException ex, Locale locale) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidEx) {
            Class<?> targetType = invalidEx.getTargetType();

            if (targetType.isEnum()) {
                String message = messageSource.getMessage("exception.enum.invalid",
                        new Object[]{targetType.getSimpleName(), invalidEx.getValue(), Arrays.toString(
                                targetType.getEnumConstants())}, locale
                                                         );

                return ResponseEntity
                        .badRequest()
                        .body(message);
            }
        }

        String defaultMessage = messageSource.getMessage(
                "exception.request.invalid", null, locale);
        return ResponseEntity
                .badRequest()
                .body(defaultMessage);
    }
}
