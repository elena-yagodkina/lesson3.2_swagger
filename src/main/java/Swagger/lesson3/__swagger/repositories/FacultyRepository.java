package Swagger.lesson3.__swagger.repositories;

import Swagger.lesson3.__swagger.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
