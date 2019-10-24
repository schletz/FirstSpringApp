package com.spengergasse.firstapp.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

// AbstractPersistable: Grundlegende Methoden zum Laden des Primärschlüssels
// und zum Vergleichen von Objekten. Legt automatisch ein Feld id für den Schlüssel an.

// Lombock Annotations
@Data
@NoArgsConstructor    // Zum Instanzieren für Spring Data.
@AllArgsConstructor   // Für den Builder in Spring Data.
@Builder              // Getter und Setter für alle Attribute

// Spring Persistence Annotations
@Entity
@Table(name = "teachers")
public class Teacher extends AbstractPersistable<Long> {
    // Optimistic Locking. JPA erzeugt eine Versionsnummer. Der Zugriff erfolgt dann immer
    // mit ID und Versionsnummer, somit muss die Datenbank nicht sperren.
    @Version                      
    private Integer version;

    @Column(name="first_name", length = 100, nullable = false)
    @Size(min = 2, max = 100)
    @NotNull    
    @NonNull
    private String firstname;

    @Column(name="last_name", length = 100, nullable = false)
    @Size(min = 2, max = 100)
    @NotNull    
    @NonNull    
    private String lastname;

    // Mit @Enumerated ohne Option wird die Ordinalzahl gespeichert (1: Female, ...)
    // @Convert(converter = GenderConverter.class)    
    private Gender gender;

    // Fasst alle Attribute zu einem Objekt zusammen.
    @Embedded
    @NotNull
    @NonNull
    private PhoneNumber phoneNumber;

    // Rücknavigation nur wenn es benötigt wird.
    @OneToMany
    private List<Course> courses;
}