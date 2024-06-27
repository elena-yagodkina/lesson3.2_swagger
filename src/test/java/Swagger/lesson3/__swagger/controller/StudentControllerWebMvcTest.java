package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.repositories.StudentRepository;
import Swagger.lesson3.__swagger.service.AvatarService;
import Swagger.lesson3.__swagger.service.FacultyService;
import Swagger.lesson3.__swagger.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private AvatarService avatarService;

    @MockBean
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void getStudentByIdTest() throws Exception {
        Student expected = new Student(1L, "Иван Иванов", 14);
        when(studentService.getStudentById(1L)).thenReturn(new Student(1L, "Иван Иванов", 14));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.age").value(expected.getAge()));
    }

    @Test
    public void createStudentTest() throws Exception {
        Student expected = new Student(1L, "Андрей Федоров", 14);
        when(studentService.createStudent(new Student(1L,"Андрей Федоров", 14))).thenReturn(new Student(1L,"Андрей Федоров", 14));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(new ObjectMapper().writeValueAsString(new Student(1L,"Андрей Федоров", 14)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.age").value(expected.getAge()));
    }

    @Test
    void updateStudentTest() throws Exception {
        Student expected = new Student(1L, "Анна Борисова", 13);
        when(studentService.updateStudent(new Student(1L,"Анна Борисова", 13))).thenReturn(new Student(1L,"Анна Борисова", 13));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(new ObjectMapper().writeValueAsString(new Student(1L,"Анна Борисова", 13)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.age").value(expected.getAge()));
    }

    @Test
    void getAll() throws Exception {
        List<Student> students = List.of(
                new Student("Сергей Иванов", 11),
                new Student("Роман Григорьев", 12),
                new Student("Ольга Сергеева", 13),
                new Student("Андрей Смирнов", 14),
                new Student("Ксения Захарова", 15));
        when(studentService.getAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(students.get(0)))
                .andExpect(jsonPath("$[1]").value(students.get(1)))
                .andExpect(jsonPath("$[2]").value(students.get(2)))
                .andExpect(jsonPath("$[3]").value(students.get(3)))
                .andExpect(jsonPath("$[4]").value(students.get(4)));
    }

    @Test
    void getByAgeBetweenTest() throws Exception {
        int minAge = 12;
        int maxAge = 15;
        List<Student> students = List.of(
                new Student("Сергей Иванов", 11),
                new Student("Роман Григорьев", 12),
                new Student("Ольга Сергеева", 13),
                new Student("Андрей Смирнов", 14),
                new Student("Ксения Захарова", 15));
        when(studentService.getByAgeBetween(any(Integer.class), any(Integer.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .param("minAge", String.valueOf(minAge))
                        .param("maxAge", String.valueOf(maxAge))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(students.get(0)))
                .andExpect(jsonPath("$[1]").value(students.get(1)));
    }

    @Test
    void getFacultyOfStudent() throws Exception {
        Student student = new Student(1L, "Анна Борисова", 13);
        Faculty faculty = new Faculty(1L, "История", "Синий");

        student.setFaculty(faculty);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost/student/1/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }
}
