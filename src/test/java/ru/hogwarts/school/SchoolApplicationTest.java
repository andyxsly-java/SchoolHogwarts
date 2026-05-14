package ru.hogwarts.school;

import nonapi.io.github.classgraph.json.Id;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.awt.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchoolApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetStudents() throws Exception {
        Assertions.assertThat(this.facultyController.getFaculty(3));
    }

    @Test
    public void testGetObject() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/id", String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostObject() throws Exception {
        Student student = new Student (5L,"Волан-де-Морт", 126);
        student.setName("Волан-де-Морт");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/", student, String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudents() throws Exception {
        Student student = new Student (5L,"Волан-де-Морт", 126);
        student.setName("Волан-де-Морт");
        Assertions
                .assertThat(this.facultyController.createFaculty(new Faculty(5L, "Пятый факультет", "чёрный")));
    }

    @Test
    public void testPutStudents() throws Exception {
        Assertions
                .assertThat(this.facultyController.updateFaculty(new Faculty(4L, "Когтевран", "синий")))
                .isNotNull();

    }

    @Test
    public void testDeleteStudents() throws Exception {
        Faculty faculty = new Faculty(5, "Пятый факультет", "чёрный");
        Assertions
                .assertThat(this.facultyController.deleteFaculty(5));
    }
}
