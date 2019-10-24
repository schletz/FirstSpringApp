package com.spengergasse.firstapp.presentation.web;

import java.util.List;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String showStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return (students.size() == 1) ? "students/detail" : "students/list";
    }
}