package com.spengergasse.firstapp.presentation.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Definiert ein Advice. Das bedeutet, dass bestimmte Situationen in den
 * Controllern überwacht werden. Wir benutzen das Überwachen von Exceptions, auf
 * die wir mit einer benutzerdefinierten Antwort reagieren.
 */
// Berücksichtigt nur Controller, die im aktuellen Paket oder darunter liegen. ALso nicht die api
// controller.
@ControllerAdvice(basePackageClasses = { GlobalWebControllerAdvice.class})
public class GlobalWebControllerAdvice {
    // Stellt in Thymeleaf ein Attribut renderTS bereit, das in der folgenden Methode berechnet wird.
    @ModelAttribute("renderTS")
    public String serverRenderTS() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @ExceptionHandler
    // Bei Exceptions wird automatisch nur error zurückgegeben und kein Stacktrace (Sicherheitslücke)
    public String handleThrowable(Throwable t) {
        return "error";
    }
}