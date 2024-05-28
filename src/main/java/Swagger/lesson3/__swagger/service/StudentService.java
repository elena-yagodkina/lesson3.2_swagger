package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Student;

import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(Long studentId);

    Student updateStudent(Long studentId, Student student);

    Student deleteStudent(Long studentId);

    Collection<Student> getByAge(int age);

    Collection<Student> getAll();
}
