package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@OpenAPIDefinition

public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
}
