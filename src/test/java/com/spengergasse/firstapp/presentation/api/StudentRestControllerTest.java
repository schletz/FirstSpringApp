package com.spengergasse.firstapp.presentation.api;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import com.spengergasse.firstapp.domain.Student;
import com.spengergasse.firstapp.service.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

/**
 * Prüft den REST Controller.
 * https://spring.io/guides/gs/testing-web/
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentRestController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Eigenen Servicelayer mocken.
    @MockBean
    private StudentService studentService;

    // Prüft, ob der REST Controller bei der Route GET /api/students ein JSON mit 2
    // Elementen liefert.
    @Test
    void whenValidInput_thenReturn200() throws Exception {
        Student klaus = Student.builder().firstname("Klaus").lastname("Unger")
                .registrationTS(LocalDateTime.of(2019, 1, 1, 12, 0)).build();
        Student michael = Student.builder().firstname("Michael").lastname("Schletz")
                .registrationTS(LocalDateTime.of(2019, 1, 1, 12, 0)).build();

        when(studentService.getAllStudents()).thenReturn(List.of(klaus, michael));

        mockMvc.perform(get("/api/students").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
    }
}
