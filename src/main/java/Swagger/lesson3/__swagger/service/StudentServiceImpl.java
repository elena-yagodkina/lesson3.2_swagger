package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.exception.StudentNotFoundExceptionById;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            logger.debug("Requesting student for studentId = {}", studentId);
            return student.get();
        }
        else
            logger.error("There is not student with id = " + studentId);
            throw new StudentNotFoundExceptionById(studentId);
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student with studentId = {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("Was invoked method for getting all students");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getByAgeBetween(int min, int max) {
        Collection<Student> students = studentRepository.findByAgeBetween(min, max);
        if (students.isEmpty()) {
            return Collections.emptyList();
        }
        logger.info("Was invoked method for getting students whose age is between {} and {}", min, max);
        return students;
    }

    @Override
    public Integer getAmountOfStudents() {
        logger.info("Was invoked method for amount of students");
        return studentRepository.getAmountOfStudents();
    }

    @Override
    public Double getAverageAge() {
        logger.info("Was invoked method for getting average age of students");
        return studentRepository.getAverageAge();
    }

    @Override
    public List<Student> getFiveLastStudents(Integer amount) {
        logger.info("Was invoked method for getting five last of students");
        return studentRepository.getFiveLastStudents();
    }
}
