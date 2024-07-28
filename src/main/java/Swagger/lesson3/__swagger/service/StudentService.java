package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(long id);

    Student updateStudent(Student student);

    void deleteStudent(long id);

    Collection<Student> getAll();

    Collection<Student> getByAgeBetween(int min, int max);

    Integer getAmountOfStudents();

    Double getAverageAge();

    List<Student> getFiveLastStudents(Integer amount);

    List<String> getStudentStartWithLetter();

    Double getAvgStudentAge();

    List<Student> printStudentsNames();

    List<Student> printStudentsNamesSync();
}
