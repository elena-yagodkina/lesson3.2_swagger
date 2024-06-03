package Swagger.lesson3.__swagger.repositories;

import Swagger.lesson3.__swagger.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
