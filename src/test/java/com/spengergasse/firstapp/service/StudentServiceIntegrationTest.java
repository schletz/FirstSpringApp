package com.spengergasse.firstapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import com.spengergasse.firstapp.domain.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Beim Integration Test verwenden wir die echte Datenbank.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StudentServiceIntegrationTest {
    @Autowired
    StudentService studentService;

    @BeforeEach
    void setuo() {
        assumeTrue(studentService != null);
    }

    @Test
    void ensureGetAllStudentWorksProperly() {
        // when
        List<Student> students = studentService.getAllStudents();

        //then
        assertTrue(students != null);
        assertTrue(students.size() == 2);
    }
}