package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long generatedStudentId = 0;

    @Override
    public Student createStudent(Student student) {
        students.put(generatedStudentId, student);
        generatedStudentId++;
        return student;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        students.put(generatedStudentId, student);
        return student;
    }

    @Override
    public Student deleteStudent(Long studentId) {
        return students.remove(studentId);
    }

    @Override
    public Collection<Student> getByAge(int age) {

        return students.values().stream().filter(s -> s.getAge() == age).toList();
    }

    @Override
    public Collection<Student> getAll() {
        return Collections.unmodifiableCollection(students.values());
    }
}
