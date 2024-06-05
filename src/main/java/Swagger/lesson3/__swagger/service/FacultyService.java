package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty getFacultyById(long id);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> getAll();
}
