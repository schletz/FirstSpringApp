package com.spengergasse.firstapp.service;

import com.spengergasse.firstapp.domain.Gender;
import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.persistence.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor

/**
 * Servicelayer für unsere Student Applikation. Dieser Service wird in den Controllern instanziert
 * und er stellt die Daten für die Anzeige bereit.
 */
@Service
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    // Liefert eine Liste aller Studenten in der Datenbank.
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Wird beim Instanzieren automatisch ausgeführt und generiert in diesem Fall Musterdaten.
    @PostConstruct
    @Transactional(readOnly = true)
    public void initialize() {
        Student s1 = Student.builder().firstname("Klaus").lastname("Unger").gender(Gender.MALE).registrationTS(LocalDateTime.now()).build();
        Student s2 = Student.builder().firstname("Joachim").lastname("Grüneis").gender(Gender.MALE).registrationTS(LocalDateTime.now()).build();
        studentRepository.saveAll(List.of(s1, s2));
    }
}