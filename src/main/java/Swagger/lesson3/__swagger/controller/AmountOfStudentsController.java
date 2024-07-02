package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AmountOfStudentsController {

    private final StudentService studentService;

    public AmountOfStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/amount-of-students")
    public Integer getAmountOfStudents() {
        return studentService.getAmountOfStudents();
    }

    @GetMapping("/average-age-of-students")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/five-last-students")
    public List<Student> getFiveLastStudents() {
        return studentService.getFiveLastStudents();
    }
}
