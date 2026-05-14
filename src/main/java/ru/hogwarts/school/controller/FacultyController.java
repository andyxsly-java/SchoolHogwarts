package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import java.util.Collection;

@RequestMapping("faculties")
@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}/students")
    public Faculty getStudentsByFacultyId(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }


    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("/color")
    public ResponseEntity findFacultyByColor(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultyByColor(color));
    }
        return ResponseEntity.ok(facultyService.getByColor(color));
}

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFaculty() {
        return ResponseEntity.ok(facultyService.getFaculty());
    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty.getId(), faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }
}
