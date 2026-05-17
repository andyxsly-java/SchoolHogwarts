package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacultyRepository facultyRepository;

    @MockitoBean
    private AvatarRepository avatarRepository;

    @MockitoSpyBean
    private FacultyService facultyService;

    @MockitoSpyBean
    private AvatarService avatarService;

    @InjectMocks
    private FacultyController facultyController;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "Гриффиндор", "красный");

        when(facultyService.createFaculty(any(Faculty.class)))
                .thenReturn(faculty);

        mockMvc.perform(post("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Гриффиндор"));
    }

    @Test
    void getFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "Гриффиндор", "красный");

        when(facultyService.findFaculty(1L))
                .thenReturn(faculty);

        mockMvc.perform(get("/faculties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("красный"));
    }

    @Test
    void updateFaculty() throws Exception {
        Faculty faculty = new Faculty(2L, "Слизерин", "фиолетовый");

        when(facultyService.updateFaculty(any(Long.class), any(Faculty.class)))
                .thenReturn(faculty);

        mockMvc.perform(put("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("фиолетовый"));
    }

    @Test
    void deleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculties/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getFacultyByColor() throws Exception {
        Faculty faculty = new Faculty(1L, "Гриффиндор", "красный");

        when(facultyService.findFacultyByColor("красный"))
                .thenReturn(String.valueOf(faculty));

        mockMvc.perform(get("/faculties/color")
                        .param("color", "красный"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Гриффиндор"));
    }

    @Test
    void getStudentsByFacultyId() throws Exception {
        Faculty faculty = new Faculty(1L, "Пуффендуй", "оранжевый");

        when(facultyService.findFaculty(1L))
                .thenReturn(faculty);

        mockMvc.perform(get("/faculties/1/students"))
                .andExpect(status().isOk());
    }
}
