package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(long FacultyId, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.save(faculty);
    }

    public String getByColor(String color) {
        logger.info("Was invoked method for get color");
        return color;
    }

    public Collection<Faculty> getFaculty() {
        logger.info("Was invoked method for get faculty");
        return facultyRepository.findAll();
    }

    public long deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        return id;
    }

    public Collection<Faculty> findFacultyByColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for find faculty by name or color ignore containing case");
        return facultyRepository.findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(name, color);
    }

    public String findFacultyByColor(String color) {
        logger.info("Was invoked method for find faculty by color");
        return color;
    }

    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method for find faculty");
        return findFaculty(id);
    }
}