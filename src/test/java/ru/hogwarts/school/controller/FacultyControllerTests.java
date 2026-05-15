package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTests {

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
        Assertions.assertThat(this.facultyController.getFaculty());
    }

    @Test
    public void testGetObject() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/id", String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostObject() throws Exception {
        Faculty faculty = new Faculty (5L,"Пятый факультет", "чёрный");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudents() throws Exception {
        Faculty faculty = new Faculty (5L,"Пятый факультет", "чёрный");
        faculty.setName();
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
