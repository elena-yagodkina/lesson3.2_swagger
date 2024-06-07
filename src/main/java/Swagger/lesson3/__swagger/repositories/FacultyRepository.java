package Swagger.lesson3.__swagger.repositories;

import Swagger.lesson3.__swagger.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findFacultyByNameOrColorIgnoreCase(String name, String color);
}
