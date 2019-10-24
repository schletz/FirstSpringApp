package com.spengergasse.firstapp.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modell Schuler <------> Course <-------| Teacher
 * Modelklasse für die Tabelle Courses
 * create table courses (
 *     id bigint not null, 
 *     description varchar(255), 
 *     end_date date, 
 *     name varchar(255), 
 *     number varchar(255), 
 *     start_date date, 
 *     teacher_id bigint, 
 *     primary key (id))
 */

// Lombock Annotations
@Data
@NoArgsConstructor    // Zum Instanzieren für Spring Data.
@AllArgsConstructor   // Für den Builder in Spring Data.
@Builder              // Stellt builder() und Getter und Setter für alle Attribute bereit.

// Spring Persistence Annotations
@Entity
@Table(name = "courses")
// AbstractPersistable: Grundlegende Methoden zum Laden des Primärschlüssels
// und zum Vergleichen von Objekten. Legt automatisch ein Feld id für den Schlüssel an.
public class Course extends AbstractPersistable<Long> {
    private String name;
    private String number;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    // Navigation Property zu Teacher (1 Lehrer hält n Kurse)
    @ManyToOne
    private Teacher teacher;
    // Erstellt eine Zwischentabelle courses_students und bildet die n:m Beziehung ab.
    @ManyToMany
    private List<Student> students; 
}