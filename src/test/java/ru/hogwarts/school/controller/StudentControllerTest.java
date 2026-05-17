package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private StudentRepository studentRepository;

    private AvatarRepository avatarRepository;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private AvatarService avatarService;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createStudent() throws Exception {
        Student student = new Student(5L, "Волан-де-Морт", 126);

        when(studentService.createStudent(any(Student.class)))
                .thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Волан-де-Морт"))
                .andExpect(jsonPath("$.age").value(126));
    }

    @Test
    void getStudent() throws Exception {
        Student student = new Student(1L, "Гарри Поттер", 11);

        when(studentService.findStudent(1L))
                .thenReturn(student);

        mockMvc.perform(get("/students/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Гарри Поттер"));
    }

    @Test
    void updateStudent() throws Exception {
        Student updatedStudent = new Student(1L, "Гарри Поттер", 11);

        when(studentService.updateStudent(any(Student.class)))
                .thenReturn(updatedStudent);

        mockMvc.perform(put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Гарри Поттер"))
                .andExpect(jsonPath("$.age").value(12));

    }
    @Test
    void deleteStudent() throws Exception {

        doNothing().when(studentService).deleteStudent(5L);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());
    }


}
