package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Avatar;
import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.service.AvatarService;
import Swagger.lesson3.__swagger.service.FacultyService;
import Swagger.lesson3.__swagger.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;
    private final FacultyService facultyService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, FacultyService facultyService, AvatarService avatarService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.avatarService = avatarService;
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
    public ResponseEntity<Student> updateUser(@RequestBody Student student) {
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
        Faculty faculty = studentService.get(id).getFaculty();
        return ResponseEntity.ok(faculty);
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
    }

    @GetMapping(value = "/{id}/avatar/")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}
