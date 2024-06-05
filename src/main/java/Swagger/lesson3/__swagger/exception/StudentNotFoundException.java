package Swagger.lesson3.__swagger.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(long message) {
        super(String.valueOf(message));
    }
}
