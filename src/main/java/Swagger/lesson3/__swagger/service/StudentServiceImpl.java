package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.exception.StudentNotFoundException;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long studentId) {

        return studentRepository.findById(studentId).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getByAgeBetween(int min, int max) {
        Collection<Student> students = studentRepository.findByAgeBetween(min, max);
        if (students.isEmpty()) {
            return Collections.emptyList();
        }
        return students;
    }

    @Override
    public Student get(Long id) {

        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new StudentNotFoundException(String.valueOf(id));
    }

    @Override
    public Integer getAmountOfStudents() {
        return studentRepository.getAmountOfStudents();
    }

    @Override
    public Double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    @Override
    public List<Student> getFiveLastStudents() {
        return studentRepository.getFiveLastStudents();
    }
}
