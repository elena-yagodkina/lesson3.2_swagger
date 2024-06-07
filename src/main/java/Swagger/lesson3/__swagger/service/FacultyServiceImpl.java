package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.exception.FacultyNotFoundException;
import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.repositories.FacultyRepository;
import Swagger.lesson3.__swagger.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }


    @Override
    public Faculty findFacultyByNameOrColor(String name, String color) {
        return facultyRepository.findFacultyByNameOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudentsByFaculty(Long facultyId) {
        return studentRepository.findByFaculty_Id(facultyId);
    }
}
