package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private long generatedFacultyId = 0;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculties.put(generatedFacultyId, faculty);
        generatedFacultyId++;
        return faculty;
    }

    @Override
    public Faculty getFacultyById(Long facultyId) {
        return faculties.get(facultyId);
    }

    @Override
    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        faculties.put(generatedFacultyId, faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long facultyId) {
        return faculties.remove(facultyId);
    }

    @Override
    public Collection<Faculty> getByColor(String color) {

        return faculties.values().stream().filter(s -> s.getColor().equals(color)).toList();
    }

    @Override
    public Collection<Faculty> getAll() {

        return Collections.unmodifiableCollection(faculties.values());
    }
}
