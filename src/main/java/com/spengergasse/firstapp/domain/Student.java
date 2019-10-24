package com.spengergasse.firstapp.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.*;

// AbstractPersistable: Grundlegende Methoden zum Laden des Primärschlüssels
// und zum Vergleichen von Objekten. Legt automatisch ein Feld id für den Schlüssel an.

// Lombock Annotations
@Data
@NoArgsConstructor    // Zum Instanzieren für Spring Data.
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)  // Konstruktor mit allen @NonNull Spalten.
@AllArgsConstructor   // Für den Builder in Spring Data.
@Builder              // Getter und Setter für alle Attribute

// Spring Persistence Annotations
@Entity
@Table(name = "students")
@NamedQuery(name = "Student.findRegisteredBefore", query = "select s from Student s where s.registrationTS < ?1")
public class Student extends AbstractPersistable<Long> {
    // Optimistic Locking. JPA erzeugt eine Versionsnummer. Der Zugriff erfolgt dann immer
    // mit ID und Versionsnummer, somit muss die Datenbank nicht sperren.
    @Version                
    private Integer version;
        
    // Die Angabe in @Column ist für die Schemagenerierung nötig. Über @Size wird über das Validation
    // Framework jedoch beim Setzen zur Laufzeit validiert.    
    @Column(name="first_name", length = 30, nullable = false)
    @Size(min = 2, max = 100)
    @NotNull
    @NonNull  // Für @RequiredArgsConstructor in Lombock
    private String firstname;

    @Column(name="last_name", length = 30, nullable = false)
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

    @Convert(converter = GenderConverter.class)    
    private Gender gender;     
}