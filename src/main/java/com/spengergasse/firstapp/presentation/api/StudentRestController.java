package com.spengergasse.firstapp.presentation.api;

import java.util.List;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.service.StudentService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentRestController {
    private final StudentService studentService;

    @GetMapping(ApiConstants.API_PREFIX + "/students")
    HttpEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

}

