package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty getFacultyById(long id);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> getAll();

    Faculty findFacultyByNameOrColor(String name, String color);

    Collection<Student> getStudentsByFaculty(Long facultyId);
}
