package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Student;

import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(long id);

    Student updateStudent(Student student);

    void deleteStudent(long id);

    Collection<Student> getAll();

    Collection<Student> getByAgeBetween(int min, int max);

    Student get(Long id);
}
