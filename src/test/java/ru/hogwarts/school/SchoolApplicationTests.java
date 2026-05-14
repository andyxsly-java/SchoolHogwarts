package ru.hogwarts.school;

import jakarta.persistence.Id;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchoolApplicationTests {

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
	public void testGetStudents() throws Exception {
		Assertions.assertThat(this.studentController.getStudent(1));
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
				.assertThat(this.studentController.createStudent(new Student(5L, "Волан-де-Морт", 126)));
	}

	@Test
	public void testPutStudents() throws Exception {
		Assertions
				.assertThat(this.studentController.updateStudent(new Student(4L, "Невилл Долгопупс", 10)))
				.isNotNull();

	}

	@Test
	public void testDeleteStudents() throws Exception {
		Assertions
				.assertThat(this.studentController.deleteStudent(new Student(5L, "Волан-де-Морт", 126).getId()))
				.toString();
	}
}