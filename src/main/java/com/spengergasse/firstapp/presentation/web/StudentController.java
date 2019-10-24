package com.spengergasse.firstapp.presentation.web;

import java.util.List;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/**
 * Controller, der mit Thymeleaf gerenderte Views aus src/main/resources/templates zurückliefert.
 */
@RequiredArgsConstructor
@Controller
public class StudentController {
    private final StudentService studentService;

    // Wenn nur ein Schüler vorhanden ist, wird nach students/detail.html gesucht und dieses Template
    // gerendert. Bei mehreren Einträgem wird students/list.html gerendert.
    @GetMapping
    public String showStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        // Für Thymeleaf schreiben wir in das Property students unser Abfrageergebnis.
        model.addAttribute("students", students);
        return (students.size() == 1) ? "students/detail" : "students/list";
    }
}