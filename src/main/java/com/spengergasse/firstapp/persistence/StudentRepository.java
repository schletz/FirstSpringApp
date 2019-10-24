package com.spengergasse.firstapp.persistence;

import java.util.List;
import java.time.LocalDateTime;

import com.spengergasse.firstapp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring Data generiert diese Methode automatisch. Das Argument muss exakt dem Namen
    // nach By in der Methode entsprechen.
    public List<Student> findAllByLastname(String lastName);
    public List<Student> findRegisteredBefore(LocalDateTime time);
}