package com.spengergasse.firstapp.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

/**
 * Enumeration für Gender.
 */
@RequiredArgsConstructor
public enum Gender {
    FEMALE("F"), MALE("M"), LEGAL("L"), TRANSSEXUAL("T");

    private final String code;

    // Zur Demonstration, wird nicht verwendet.
    public boolean isFemale() {
        return this == FEMALE;
    }

    // Zur Demonstration, wird nicht verwendet.
    public Predicate<Gender> isFemalePredicate() {
        return x -> x == FEMALE;
    }

    // Liefert den enum Wert (F, M, ..) zum übergebenen Code. Wird in GenderConverter.java
    // verwendet.
    public static Gender forCode(String code) {
        Objects.requireNonNull(code, "code must not be null");

        return Arrays
            .stream(values())
            .filter(g -> g.getCode().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("Unsupported Code! Known codes are '%s', but you provided '%s'",
                    getCodes(), code)));

	}

    // Liefert eine mit, getrennte Liste aller Codes.
    private static String getCodes() {
        return Arrays
            .stream(values())
            .map(g -> g.getCode())
            .collect(Collectors.joining(", "));
    }

    // Liefert den Buchstabencode (F, M, ...)
    public String getCode()  {
        return code;
    }
}