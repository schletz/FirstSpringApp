package com.spengergasse.firstapp.presentation.api;

import java.util.List;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.service.StudentService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller, der auf /api/students reagiert und ein JSON mit allen eingetragenen Students
 * liefert.
 */
@RequiredArgsConstructor
@RestController
public class StudentRestController {
    // Zugriff auf den Servicelayer. Ruft initialize() auf und generiert Musterdaten.
    private final StudentService studentService;

    // Ragieren auf GET api/students
    @GetMapping(ApiConstants.API_PREFIX + "/students")
    HttpEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getAllStudents();
        // HTTP 200 + Daten als JSON liefern.
        return ResponseEntity.ok(students);
    }

}

