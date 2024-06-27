package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.repositories.StudentRepository;
import Swagger.lesson3.__swagger.service.AvatarService;
import Swagger.lesson3.__swagger.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private AvatarService avatarService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void getFacultyByIdTest() throws Exception {
        Faculty expected = new Faculty(1L, "Химия", "Белый");
        when(facultyService.getFacultyById(1L)).thenReturn(new Faculty(1L, "Химия", "Белый"));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.color").value(expected.getColor()));
    }

    @Test
    public void createFacultyTest() throws Exception {
        Faculty expected = new Faculty(1L, "Математика", "Серый");
        when(facultyService.createFaculty(new Faculty(1L,"Математика", "Серый"))).thenReturn(new Faculty(1L,"Математика", "Серый"));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(new ObjectMapper().writeValueAsString(new Faculty(1L,"Математика", "Серый")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.color").value(expected.getColor()));
    }

    @Test
    void updateFacultyTest() throws Exception {
        Faculty expected = new Faculty(1L, "История", "Синий");
        when(facultyService.updateFaculty(new Faculty(1L,"История", "Синий"))).thenReturn(new Faculty(1L,"История", "Синий"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(new ObjectMapper().writeValueAsString(new Faculty(1L,"История", "Синий")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.color").value(expected.getColor()));
    }

    @Test
    void getAll() throws Exception {
        List<Faculty> faculties = List.of(
                new Faculty("История", "Синий"),
                new Faculty("Математика", "Серый"),
                new Faculty("Химия", "Зеленый"),
                new Faculty("Биология", "Коричневый"),
                new Faculty("Физика", "Красный"));
        when(facultyService.getAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(faculties.get(0)))
                .andExpect(jsonPath("$[1]").value(faculties.get(1)))
                .andExpect(jsonPath("$[2]").value(faculties.get(2)))
                .andExpect(jsonPath("$[3]").value(faculties.get(3)))
                .andExpect(jsonPath("$[4]").value(faculties.get(4)));
    }

    @Test
    void getStudentsByFacultyTest() throws Exception {
        Student student1 = new Student(1L, "Анна Борисова", 13);
        Student student2 = new Student(2L, "Виктор Сергеев", 12);
        Faculty faculty = new Faculty(1L, "История", "Синий");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        student1.setFaculty(faculty);
        student2.setFaculty(faculty);

        when(studentRepository.findByFaculty_Id(1L)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty" + "/1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Анна Борисова"))
                .andExpect(jsonPath("$[0].age").value(13))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Виктор Сергеев"))
                .andExpect(jsonPath("$[1].age").value(12));
    }
}
