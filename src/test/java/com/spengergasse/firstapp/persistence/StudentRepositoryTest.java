package com.spengergasse.firstapp.persistence;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spengergasse.firstapp.domain.Student;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentApplicationTest {
    @Autowired                   // DI nur für Tests.
    private StudentRepository studentRepository;

    @BeforeAll
    static void setupClass() {
    }

    @BeforeEach
    void startup() {
    }

    @Test
    void ensureSaveAndReloadOfStudentWorksProperty() {
        // given / arrange
        Student student = Student.builder().firstname("Michael").lastname("Schletz").registrationTS(LocalDateTime.now()).build();

        // when / set
        Student savedStudent = studentRepository.save(student);

        // then / assert
        assertTrue(savedStudent != null);
    }

    @Test
    void testNamedQuery() {
        Student student = Student.builder()
            .firstname("Michael")
            .lastname("Schletz")
            .registrationTS(LocalDateTime.of(2019, 10, 1, 0, 0))
            .build();
        studentRepository.save(student);

        List<Student> result = studentRepository.findRegisteredBefore(LocalDateTime.of(2020, 1, 1, 0, 0));
        assertTrue(result.size() == 1);
    }
    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownClass() {
    }
}
