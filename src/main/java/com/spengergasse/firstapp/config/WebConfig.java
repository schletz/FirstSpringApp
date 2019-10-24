package com.spengergasse.firstapp.config;

import com.spengergasse.firstapp.domain.Gender;
import com.spengergasse.firstapp.presentation.web.GenderFormatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Allgemeine Konfiguration von Spring MVC.
 * 
 * Registriert den Formatter für den Datentyp Gender. Dieser regelt die Darstellung
 * in Thymeleaf und wird automatisch aufgerufen, wenn ein Wert vom Typ Gender dargestellt wird.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatterForFieldType(Gender.class, new GenderFormatter());
    }
}
