package com.spengergasse.firstapp.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Modelklasse für die Tabelle teachers.
 * create table teachers (
 *     id bigint not null, 
 *     first_name varchar(100) not null, 
 *     gender integer, 
 *     last_name varchar(100) not null, 
 *     area_code integer not null, 
 *     country_code varchar(255) not null, 
 *     serial_number varchar(255) not null, 
 *     version integer, 
 *     primary key (id))
 */
// Lombock Annotations
@Data
@NoArgsConstructor    // Zum Instanzieren für Spring Data.
@AllArgsConstructor   // Für den Builder in Spring Data.
@Builder              // Stellt builder() und Getter und Setter für alle Attribute bereit.

// Spring Persistence Annotations
@Entity
@Table(name = "teachers")
// AbstractPersistable: Grundlegende Methoden zum Laden des Primärschlüssels
// und zum Vergleichen von Objekten. Legt automatisch ein Feld id für den Schlüssel an.
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
    // Die enum Gender soll vor dem Schreiben und nach dem Lesen durch den GenderConverter 
    // zwischen Ordinalwert und String umgewandelt werden.    
    @Convert(converter = GenderConverter.class)    
    private Gender gender;

    // Embedded: Fasst die Spalten last_name , area_code und country_code zu einem Objekt zusammen.
    @Embedded
    @NotNull
    @NonNull
    private PhoneNumber phoneNumber;

    // Rücknavigation zu der Tabelle courses. Sollte nur wenn sie benötigt wird angelegt werden.
    @OneToMany
    private List<Course> courses;
}