package com.spengergasse.firstapp.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// AbstractPersistable: Grundlegende Methoden zum Laden des Primärschlüssels
// und zum Vergleichen von Objekten. Legt automatisch ein Feld id für den Schlüssel an.

// Lombock Annotations
@Data
@NoArgsConstructor    // Zum Instanzieren für Spring Data.
@AllArgsConstructor   // Für den Builder in Spring Data.
@Builder              // Getter und Setter für alle Attribute

// Spring Persistence Annotations
@Entity
@Table(name = "courses")
public class Course extends AbstractPersistable<Long> {
    private String name;
    private String number;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Teacher teacher;
    @OneToMany
    private List<Student> students;  // Set, aber List wird von JPA besser unterstützt.
}