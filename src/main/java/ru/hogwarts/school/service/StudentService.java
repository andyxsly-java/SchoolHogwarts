package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long generatedStudentId = 1L;

    public Student createStudent(Student student) {
        students. put(generatedStudentId, student);
        generatedStudentId++;
        return student;
    }

    public Student getStudentById(long studentId) {
        return students.get(studentId);
    }

    public Student updateStudent(long studentId, Student student) {
        if (students.containsKey(student.getId())) {
            students.put(generatedStudentId, student);
            return student;
        }
        return null;
    }
    public Student deleteStudent(long studentId) {
        return students.remove(studentId);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }
}
