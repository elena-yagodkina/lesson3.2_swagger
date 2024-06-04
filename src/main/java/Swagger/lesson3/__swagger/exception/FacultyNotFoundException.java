package Swagger.lesson3.__swagger.exception;

public class FacultyNotFoundException extends RuntimeException{
    public FacultyNotFoundException(long message) {
        super(String.valueOf(message));
    }
}
