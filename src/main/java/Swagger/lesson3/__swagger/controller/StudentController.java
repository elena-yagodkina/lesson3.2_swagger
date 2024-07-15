package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.exception.StudentNotFoundException;
import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.service.FacultyService;
import Swagger.lesson3.__swagger.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;
    private final FacultyService facultyService;

    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "/getByAgeBetween", params = {"minAge", "maxAge"})
    public ResponseEntity<Collection<Student>> getByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        Collection<Student> students = studentService.getByAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getFacultyOfStudent(@PathVariable Long id) {
        Faculty faculty = studentService.getStudentById(id).getFaculty();
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/last")
    public ResponseEntity<List<Student>> getLastFive(@RequestParam(defaultValue="5") Integer amount) {
        List<Student> students = studentService.getFiveLastStudents(amount);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/amount")
    public ResponseEntity<Integer> getAmountStudent() {
        Integer amount = studentService.getAmountOfStudents();
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/names-starting-with/{letter}")
    public List<String> getStudentsNamesStartingWith(@PathVariable String letter) {
        if(letter.length() > 1){
           throw new StudentNotFoundException(letter);
        }
        return studentService.getStudentStartWithLetter();
    }

    /*@GetMapping("/print-parallel")
    public ResponseEntity<List<Student>> printStudentsNamesParallel() {
        List<Student> students = studentService.printStudentsNames();
        return ResponseEntity.ok(students);
    }*/

    @GetMapping("/print-parallel")
    public void printStudentsNamesParallel() {
        studentService.printStudentsNames();
    }

    @GetMapping("/print-synchronized")
    public void printStudentsNamesSync() {
        studentService.printStudentsNamesSync();
    }
}
