package Swagger.lesson3.__swagger.repositories;

import Swagger.lesson3.__swagger.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByFaculty_Id(Long id);
}
