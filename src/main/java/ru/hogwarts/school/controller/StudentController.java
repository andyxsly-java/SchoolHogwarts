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
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("{id}/faculty")
    public Student getFacultyByStudentId(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long studentId) {
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
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
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
                .orElse(0);
        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("/students/print-parallel")
    public ResponseEntity<String> getStudentsParallel() {
        List<Student> students = studentRepository.findAll();

        students.subList(0, 2)
                .forEach(student -> System.out.println(
                        Thread.currentThread().getName() + " -> " + student.getName()));

        Thread thread1 = new Thread(() ->
                students.subList(2, 4).forEach(student -> System.out.println(
                        Thread.currentThread().getName() + " -> " + student.getName())));

        Thread thread2 = new Thread(() ->
                students.subList(4, 6).forEach(student -> System.out.println(
                        Thread.currentThread().getName() + " -> " + student.getName())));

        thread1.start();
        thread2.start();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/print-synchronized")
    public ResponseEntity<Void> getStudentsSyn(String name) throws InterruptedException {
        List<Student> students = studentRepository.findAll();

        students.subList(0, 2)
                .forEach(student -> {
                    try {
                        getStudentsSyn(student.getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        Thread thread1 = new Thread(() ->
                students.subList(2, 4)
                        .forEach(student -> {
                            try {
                                getStudentsSyn(student.getName());
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
        );

        Thread thread2 = new Thread(() ->
                students.subList(4, 6)
                        .forEach(student -> {
                            try {
                                getStudentsSyn(student.getName()).getBody();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
        );

        thread1.start();
        thread2.start();

        return ResponseEntity.ok().build();
    }

        private synchronized void printName(String name) {
            System.out.println(Thread.currentThread().getName() + "->" + name);

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