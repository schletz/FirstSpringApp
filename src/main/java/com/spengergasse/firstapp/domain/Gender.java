package com.spengergasse.firstapp.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

// Die Kürzel werden in der Datenbank gespeichert.
@RequiredArgsConstructor
public enum Gender {
    FEMALE("F"), MALE("M"), LEGAL("L"), TRANSSEXUAL("T");

    private final String code;

    public boolean isFemale() {
        return this == FEMALE;
    }

    public Predicate<Gender> isFemalePredicate() {
        return x -> x == FEMALE;
    }

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

    private static String getCodes() {
        return Arrays
            .stream(values())
            .map(g -> g.getCode())
            .collect(Collectors.joining(", "));
    }

    public String getCode()  {
        return code;
    }
}