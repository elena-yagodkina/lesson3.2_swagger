package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Роман Иванов");
        student.setAge(10);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        ResponseEntity<Student> newStudent = restTemplate.postForEntity("http://localhost:" + port + "/student", new Student("студент", 12), Student.class);
        assertThat(newStudent.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Student> facultyResponse = restTemplate.getForEntity("http://localhost:" + port + "/student/" + newStudent.getBody().getId(), Student.class);

        assertThat(facultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyResponse.getBody()).isNotNull();
        assertThat(facultyResponse.getBody().getName()).isEqualTo("студент");
        assertThat(facultyResponse.getBody().getAge()).isEqualTo(12);
    }

    @Test
    public void testUpdateStudent() throws Exception {
        final Student updated = new Student(1L, "Роман Иванов", 10);

        final ResponseEntity<Student> response = restTemplate.exchange(
                String.format("http://localhost:" + port + "/student", port),
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                Student.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/2/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAgeBetween() throws Exception {
        ResponseEntity<List<Student>> students =
                restTemplate.exchange("http://localhost:" + port + "/student/getByAgeBetween?minAge=" + 10 + "&maxAge=" + 15,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                        });
    }

    @Test
    public void deleteStudentTest() {
        ResponseEntity<Student> newStudent = restTemplate.postForEntity("http://localhost:" + port + "/student", new Student("Студент", 11), Student.class);
        Long id = newStudent.getBody().getId();

        restTemplate.delete("http://localhost:" + port + "/student" + "?id=" + id);
        String actual = restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + id, String.class);

        assertThat(actual).contains("Студент с id=" + id + " не найден");
    }

}
