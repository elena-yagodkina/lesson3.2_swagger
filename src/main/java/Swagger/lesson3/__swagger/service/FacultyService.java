package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty getFacultyById(Long facultyId);

    Faculty updateFaculty(Long facultyId, Faculty faculty);

    Faculty deleteFaculty(Long facultyId);

    Collection<Faculty> getByColor(String color);

    Collection<Faculty> getAll();
}
