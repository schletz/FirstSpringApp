package com.spengergasse.firstapp.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

/**
 * Fügt die Spalten country_code, area_code und serial_number zu einem Objekt zusammen.
 * Gibt es mehrere Adressen, so muss mit @AttributeOverrides die Spalten z. B. der Rechnungs und der 
 * Lieferadresse angegeben werden.
 * 
 * Bei einem Integer countryCode sollte ein Builder mit einer Generierung vom String (0043)
 * geschrieben werden. Dafür schreibt man einen Konstruktor mit dem String
 * Argument für countryCode.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor   // Für den Builder
@Builder              // Stellt build() zur Verfügung

@Embeddable
public class PhoneNumber {
    @Column(name="country_code", nullable = false)
    @NotNull
    @Size(min = 2, max = 5)
    private String countryCode;

    @Column(name="area_code", nullable = false)
    @NotNull  
    @Positive  
    private Integer areaCode;

    @Column(name="serial_number", nullable = false)
    @NotNull    
    private String serialNumber;
}