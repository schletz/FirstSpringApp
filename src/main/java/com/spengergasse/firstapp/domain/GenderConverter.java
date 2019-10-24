package com.spengergasse.firstapp.domain;

import javax.persistence.AttributeConverter;

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