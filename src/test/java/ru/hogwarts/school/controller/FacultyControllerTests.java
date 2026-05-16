package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

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
    public void testGetFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties", String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty(5L,"Пятый факультет", "чёрный");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculties/5", faculty, String.class))
                .isNotBlank();
    }

    @Test
    public void testPostFaculties() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Пятый факультет");
        faculty.setColor("чёрный");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("http://localhost:" + port + "/facul", faculty, Faculty.class);
        Assertions.assertThat(response.getStatusCode().value())
                .isEqualTo(200);

    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty (5L,"Пятый факультет", "чёрный");

                ResponseEntity<Faculty> response = restTemplate.exchange(
                        "http://localhost:" + port + "/faculties",
                        HttpMethod.PUT,
                        new HttpEntity<>(faculty),
                        Faculty.class);
        Assertions.assertThat(response.getBody())
            .isNotNull();
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty(5L, "Пятый факультет", "чёрный");
        Assertions
                .assertThatCode(()->this.restTemplate.delete("http://localhost:" + port + "/faculties/5"))
                .doesNotThrowAnyException();
    }
}

