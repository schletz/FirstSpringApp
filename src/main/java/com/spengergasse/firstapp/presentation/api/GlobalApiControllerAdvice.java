package com.spengergasse.firstapp.presentation.api;

import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Builder;
import lombok.Data;

@ControllerAdvice(basePackageClasses = { GlobalApiControllerAdvice.class})
public class GlobalApiControllerAdvice {
    /**
     * Liefert im Falle einer auftretenden Exception eine in JSON serialisierte
     * Instanz von ApiError.
     * @param t
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public HttpEntity<ApiError> handleThrowable(Throwable t) {
        ApiError body = ApiError.builder().errorCode(-1000).message(t.getMessage()).build();
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