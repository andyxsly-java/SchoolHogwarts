package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultys = new HashMap<>();
    private long generatedFacultyId = 1L;
    public long facultyId = 0;

    public Faculty createFaculty(Faculty faculty) {
        facultys. put(generatedFacultyId, faculty);
        generatedFacultyId++;
        return faculty;
    }

    public Faculty getFacultyById(long facultyId) {
        return facultys.get(facultyId);
    }

    public Faculty updateFaculty(long facultyId, Faculty faculty) {
        this.facultyId = facultyId;
        facultys.put(generatedFacultyId, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long facultyId) {
        return facultys.remove(facultyId);
    }

    public Collection<Faculty> getAllFacultys() {
        return facultys.values();
    }
}
