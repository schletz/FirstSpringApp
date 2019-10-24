package com.spengergasse.firstapp.presentation;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

import com.spengergasse.firstapp.domain.Gender;
import org.springframework.format.Formatter;

public class GenderFormatter implements Formatter<Gender> {
    @Override
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