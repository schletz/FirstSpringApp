package com.spengergasse.firstapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.anyCollectionOf;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.persistence.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Testen mit Mockito. Muss mit Test enden.
 */
@ExtendWith({ MockitoExtension.class, })
class StudentServiceTest {
    // Einen Mock erzeugen, dass das Objekt auch ohne DB getestet werden kann.
    @Mock
    StudentRepository studentRepository;

    StudentService studentService;

    @BeforeEach
    void setup() {
        // Konstruktor injection des gemockten Repositories.
        assumeTrue(studentRepository != null);
        studentService = new StudentService(studentRepository);
    }

    // Testet die Methode getAllStudents() in StudentService.
    @Test
    void ensureGetAllStudentWorksProperly() {
        // given
        // Kann in eine Klasse DOmainFixtures ausgelagert werden, die mit
        // public static Student klaus() einen Student liefert.
        // Konfiguriert das Mock Objekt für die Methode findAll, sodass sie 2 Datensätze
        // liefert.
        // (Behaviour Injection)
        Student klaus = Student.builder().firstname("Klaus").lastname("Unger")
                .registrationTS(LocalDateTime.of(2019, 1, 1, 12, 0)).build();
        Student michael = Student.builder().firstname("Michael").lastname("Schletz")
                .registrationTS(LocalDateTime.of(2019, 1, 1, 12, 0)).build();
        when(studentRepository.findAll()).thenReturn(List.of(klaus, michael));
        // Mocking von void Methoden
        // doReturn(method()).when(...)

        // When
        List<Student> students = studentService.getAllStudents();

        // Then
        assertTrue(students != null);
        assertTrue(students.containsAll(List.of(klaus, michael)));

        // Es darf nur 1x findAll aufgerufen werden.
        verify(studentRepository, atMostOnce()).findAll();
        // Es dürfen keine anderen Methoden von studentRepository aufgerufen werden.
        verifyNoMoreInteractions(studentRepository);
    }

}