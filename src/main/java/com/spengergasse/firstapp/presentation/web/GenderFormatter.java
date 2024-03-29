package com.spengergasse.firstapp.presentation.web;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

import com.spengergasse.firstapp.domain.Gender;
import org.springframework.format.Formatter;

/**
 * Formatiert die enum Gender in Thymeleaf. Wird in firstapp/config/WebConfig.java registriert.
 */
public class GenderFormatter implements Formatter<Gender> {
    @Override
    // Zur Demonstration wird einfach der definierte enum Text in Kleinbuchstaben gewandelt.
    public String print(Gender object, Locale locale) {
        Objects.requireNonNull(object, "gender string must not be null");
        return object.name().toLowerCase();
    }

    @Override
    public Gender parse(String text, Locale locale) throws ParseException {
        Objects.requireNonNull(text, "gender string must not be null");
        return Gender.valueOf(text.toUpperCase());
    }
    
}