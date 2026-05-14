package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(long FacultyId, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public String getByColor(String color) {
        return color;
    }

    public Collection<Faculty> getFaculty() {
        return facultyRepository.findAll();
    }

    public void deleteFaculty(long facultyId) {
    }

    public Collection<Faculty> findFacultyByColorIgnoreCase(String color) {
        return facultyRepository.findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(color);
    }

    public String findFacultyByColor(String color) {
        return color;
    }

    public Faculty findFaculty(Long id) {
        return findFaculty(id);
    }
}