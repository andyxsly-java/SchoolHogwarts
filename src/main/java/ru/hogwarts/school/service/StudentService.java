package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudentById(long studentId) {
        logger.info("Was invoked method for get student");
        logger.debug("Was invoked method for get id", studentId);
        return studentRepository.getById(studentId);
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public long getStudentsCount() {
        logger.info("Was invoked method for get students count");
        return studentRepository.count();
    }

    public double getAverageAge() {
        logger.info("Was invoked method for get average age");
        Double averageAge = studentRepository.getAverageAge();
        return averageAge != null ? averageAge: 0.0;
    }

    public List<Student> getLastStudents() {
        logger.info("Was invoked method for get last student");
        return studentRepository.getLastStudents();
    }

    public Student findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find by age");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student by id");
        return getStudentById(id);
    }
}
