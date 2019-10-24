package com.spengergasse.firstapp.persistence;

import java.util.List;
import java.time.LocalDateTime;

import com.spengergasse.firstapp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Genieriert ein Defaultrepository mit den Methoden aus JpaRepository (findAll, save, ...)
 * Zusätzlich werden noch eigene Abfragemöglichkeiten implementiert.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring Data generiert diese Methode AUTOMATISCH. Sie muss exakt findAllBy(Spaltenname)
    // lauten (Groß/Kleinschreibung beachten!) und generiert 
    // SELECT * FROM ... WHERE (Spaltenname) = (übergebener Wert)
    public List<Student> findAllByLastname(String lastName);
    public List<Student> findRegisteredBefore(LocalDateTime time);
}