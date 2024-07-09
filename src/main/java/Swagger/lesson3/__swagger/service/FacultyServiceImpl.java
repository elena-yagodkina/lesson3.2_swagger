package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.exception.FacultyNotFoundException;
import Swagger.lesson3.__swagger.model.Faculty;
import Swagger.lesson3.__swagger.model.Student;
import Swagger.lesson3.__swagger.repositories.FacultyRepository;
import Swagger.lesson3.__swagger.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(long id) {
        logger.debug("Requesting faculty for id = {}", id);
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty with id = {}", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("Was invoked method for getting all faculties");
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findFacultyByNameOrColor(String name, String color) {
        logger.info("Was invoked method for getting faculty by name and color");
        return facultyRepository.findFacultyByNameOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudentsByFaculty(Long facultyId) {
        logger.info("Was invoked method for getting students by facultyId");
        return studentRepository.findByFaculty_Id(facultyId);
    }
}
