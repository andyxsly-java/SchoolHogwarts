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
public class SchoolControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student (5L,"Волан-де-Морт", 126);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotBlank();
    }

    @Test
    public void testPostStudents() throws Exception {
        Student student = new Student();
        student.setName("Волан-де-Морт");
        student.setAge(126);
        ResponseEntity<Student> response = restTemplate.postForEntity("http://localhost:" + port + "/stud", student, Student.class);
        Assertions.assertThat(response.getStatusCode().value())
                .isEqualTo(200);

    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student (5L,"Волан-де-Морт", 126);

        ResponseEntity<Student> response = restTemplate.exchange(
                "http://localhost:" + port + "/students",
                HttpMethod.PUT,
                new HttpEntity<>(student),
                Student.class);
        Assertions.assertThat(response.getBody())
                .isNotNull();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student(5L, "Волан-де-Морт", 126);
        Assertions
                .assertThatCode(()->this.restTemplate.delete("http://localhost:" + port + "/students/5"))
                .doesNotThrowAnyException();
    }
}
