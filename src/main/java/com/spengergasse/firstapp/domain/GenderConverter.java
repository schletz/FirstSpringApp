package com.spengergasse.firstapp.domain;

import javax.persistence.AttributeConverter;

/**
 * Konvertiert den enum Wert vor dem Schreiben in die Datenbank in den Stringwert (F, M, ...),
 * damit nicht der Ordinalwert eingetragen wird.
 * Beim Auslesen wird dieser Wert wieder zurückkonvertiert.
 */
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return (attribute == null) ? null : attribute.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : Gender.forCode(dbData);
    }
    
}