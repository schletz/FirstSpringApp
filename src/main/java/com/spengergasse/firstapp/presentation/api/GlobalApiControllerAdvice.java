package com.spengergasse.firstapp.presentation.api;

import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Builder;
import lombok.Data;

/**
 * Definiert ein Advice. Das bedeutet, dass bestimmte Situationen in den
 * Controllern überwacht werden. Wir benutzen das Überwachen von Exceptions, auf
 * die wir mit einer benutzerdefinierten Antwort reagieren.
 */
// Berücksichtigt nur Controller, die im aktuellen Paket oder darunter liegen. ALso nicht die web
// controller.
@ControllerAdvice(basePackageClasses = { GlobalApiControllerAdvice.class })
public class GlobalApiControllerAdvice {
    // Liefert im Falle einer auftretenden Exception eine in JSON serialisierte
    // Instanz von ApiError.
    @ExceptionHandler(Throwable.class)
    public HttpEntity<ApiError> handleThrowable(Throwable t) {
        ApiError body = ApiError.builder().errorCode(-1000).message(t.getMessage()).build();
        // Liefert 400 Bad Request mit den in ApiError enthaltenen Daten.
        return ResponseEntity.badRequest().body(body);

    }

    @Data
    @Builder
    private static class ApiError {
        private Integer errorCode;
        private String message;
        private URL documentationLink;
    }
}