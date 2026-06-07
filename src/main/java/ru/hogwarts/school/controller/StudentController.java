package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentService studentService;
    private StudentRepository studentRepository;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}/faculty")
    public Student getFacultyByStudentId(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent (@PathVariable long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity findByAge(@RequestParam(required = false) int min, @RequestParam(required = false) int max) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/count")
    public long getStudentsCount() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/students/last")
    public List<Student> getLastStudents() {
        return studentService.getLastStudents();
    }


    @GetMapping("/sortedNameStartingWithA")
    public ResponseEntity<List<String>> findAll() {
        List<Student> students = studentRepository.findAll();

        List<String> sortedNameStartingWithA = students.stream()
                .filter(student -> student.getName().toUpperCase().startsWith("A"))
                .map(Student::getName)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        return ResponseEntity.ok(sortedNameStartingWithA);
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> findAverageAge() {
        List<Student> students = studentRepository.findAll();

        double averageAge = students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow();
        return ResponseEntity.ok(averageAge);
        }

    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}