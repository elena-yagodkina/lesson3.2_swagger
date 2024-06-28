package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.service.FacultyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Математика");
        faculty.setColor("Синий");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testGetFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyById() throws Exception {
        ResponseEntity<Faculty> newFaculty = restTemplate.postForEntity("http://localhost:" + port + "/faculty", new Faculty("факультет", "black"), Faculty.class);
        assertThat(newFaculty.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Faculty> facultyResponse = restTemplate.getForEntity("http://localhost:" + port + "/faculty/" + newFaculty.getBody().getId(), Faculty.class);

        assertThat(facultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyResponse.getBody()).isNotNull();
        assertThat(facultyResponse.getBody().getName()).isEqualTo("факультет");
        assertThat(facultyResponse.getBody().getColor()).isEqualTo("black");
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        final Faculty updated = new Faculty(1L, "Химия", "Белый");

        final ResponseEntity<Faculty> response = restTemplate.exchange(
                String.format("http://localhost:" + port + "/faculty", port),
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                Faculty.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findStudentsByFacultyIdTest() {
        ResponseEntity<Faculty> newFaculty = restTemplate.postForEntity("http://localhost:" + port + "/faculty", new Faculty("Математика", "черный"), Faculty.class);
        ResponseEntity<Student> newStudent = restTemplate.postForEntity("http://localhost:" + port + "/student", new Student("студент", 12), Student.class);

        String actualStudent = restTemplate.getForObject("http://localhost:" + port + newFaculty.getBody().getId() + "/students", String.class);

        assertThat(actualStudent).contains(
                "[",
                "]",
                String.format("{\"id\":%d,\"name\":\"%s\",\"age\":%d,\"faculty\":{\"id\":%d,\"name\":", newStudent.getBody().getId(), newStudent.getBody().getName(), newStudent.getBody().getAge(), newStudent.getBody().getFaculty().getId()));
    }

    @Test
    public void deleteFacultyTest() {
        ResponseEntity<Faculty> newFaculty = restTemplate.postForEntity("http://localhost:" + port + "/faculty", new Faculty("Факультет", "синий"), Faculty.class);
        Long id = newFaculty.getBody().getId();

        restTemplate.delete("http://localhost:" + port + "/faculty" + "?id=" + id);
        String actual = restTemplate.getForObject("http://localhost:" + port + "/faculty" + "?id=" + id, String.class);

        assertThat(actual).contains("Факультет с id=" + id + " не найден");
    }
}
