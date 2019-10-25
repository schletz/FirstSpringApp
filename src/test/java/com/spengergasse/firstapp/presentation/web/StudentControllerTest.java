package com.spengergasse.firstapp.presentation.web;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.service.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

/**
 * MVC Test. Prüft, was der Controller liefert ohne auf den ursprünglichen
 * Servicelayer zuzugreifen.
 * https://spring.io/guides/gs/testing-web/
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Eigenen Servicelayer mocken.
    @MockBean
    private StudentService studentService;

    @Test
    void whenValidInput_thenReturn200() throws Exception {
        Student klaus = Student.builder().firstname("Klaus").lastname("Unger")
                .registrationTS(LocalDateTime.of(2019, 1, 1, 12, 0)).build();
        Student michael = Student.builder().firstname("Michael").lastname("Schletz")
                .registrationTS(LocalDateTime.of(2019, 1, 1, 12, 0)).build();

        when(studentService.getAllStudents()).thenReturn(List.of(klaus, michael));

        mockMvc.perform(get("/").contentType("text/html")).andExpect(status().isOk());
    }
}
