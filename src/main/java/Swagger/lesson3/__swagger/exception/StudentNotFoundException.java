package Swagger.lesson3.__swagger.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String name) {
        super("Студент с именем=" + name + " не найден");
    }
    public StudentNotFoundException(int age) {
        super("Студент с возрастом=" + age + " не найден");
    }
}
