package com.spengergasse.firstapp.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.*;


/**
 * Modelklasse für die Tabelle Schüler.
 * create table students (
 *     id bigint not null, 
 *     birth_date date, 
 *     first_name varchar(100) not null, 
 *     gender varchar(255), 
 *     last_name varchar(100) not null, 
 *     registration_ts timestamp not null, 
 *     version integer, 
 *     primary key (id))
 */
// Lombock Annotations
@Data
@NoArgsConstructor    // Zum Instanzieren für Spring Data.
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)  // Konstruktor mit allen @NonNull Spalten.
@AllArgsConstructor   // Für den Builder in Spring Data.
@Builder              // Stellt builder() und Getter und Setter für alle Attribute bereit.

// Spring Persistence Annotations
@Entity
@Table(name = "students")
// Named Query: Wird mit studentRepository.findRegisteredBefore() aufgerufen. Muss im StudentRepository
// registriert werden.
@NamedQuery(name = "Student.findRegisteredBefore", query = "select s from Student s where s.registrationTS < ?1")
// AbstractPersistable: Grundlegende Methoden zum Laden des Primärschlüssels
// und zum Vergleichen von Objekten. Legt automatisch ein Feld id für den Schlüssel an.
public class Student extends AbstractPersistable<Long> {
    // Optimistic Locking. JPA erzeugt eine Versionsnummer. Der Zugriff erfolgt dann immer
    // mit ID und Versionsnummer, somit muss die Datenbank nicht sperren.
    @Version                
    private Integer version;
        
    // Die Angabe in @Column ist für die Schemagenerierung nötig. Über @Size wird über das Validation
    // Framework jedoch beim Setzen zur Laufzeit validiert.    
    @Column(name="first_name", length = 100, nullable = false)
    @Size(min = 2, max = 100)
    @NotNull
    @NonNull  // Für @RequiredArgsConstructor in Lombock
    private String firstname;

    @Column(name="last_name", length = 100, nullable = false)
    @Size(min = 2, max = 100)
    @NotNull
    @NonNull
    private String lastname;

    @Column(name="registration_ts", nullable = false)
    @NotNull
    @NonNull
    private LocalDateTime registrationTS;
   
    @Column(name="birth_date")
    private LocalDate birthDate;

    // Die enum Gender soll vor dem Schreiben und nach dem Lesen durch den GenderConverter 
    // zwischen Ordinalwert und String umgewandelt werden.
    @Convert(converter = GenderConverter.class)    
    private Gender gender;     
}