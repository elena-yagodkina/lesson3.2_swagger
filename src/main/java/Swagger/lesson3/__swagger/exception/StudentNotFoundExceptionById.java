package Swagger.lesson3.__swagger.exception;

public class StudentNotFoundExceptionById extends RuntimeException{
    public StudentNotFoundExceptionById(long id) {
        super("Студент с id=" + id + " не найден");
    }
}
