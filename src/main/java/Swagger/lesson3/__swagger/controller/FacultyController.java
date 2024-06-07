package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if(faculty == null) {
            return ResponseEntity.notFound() .build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if(updatedFaculty == null) {
            return ResponseEntity.notFound() .build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable("facultyId") Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <Collection<Faculty>> getAll(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        if(name != null && !name.isBlank() || color != null && !color.isBlank()) {
            return ResponseEntity.ok(Collections.singleton(facultyService.findFacultyByNameOrColor(name, color)));
        }
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable Long id) {
        Collection<Student> students = facultyService.getStudentsByFaculty(id);
        return ResponseEntity.ok(students);
    }
}
