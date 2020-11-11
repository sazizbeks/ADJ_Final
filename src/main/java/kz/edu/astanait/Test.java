package kz.edu.astanait;

import kz.edu.astanait.repositories.implementations.StudentRepository;
import kz.edu.astanait.repositories.interfaces.IStudentRepository;

public class Test {
    public static void main(String[] args) {
        IStudentRepository studentRepository = new StudentRepository();
        studentRepository.getAll().forEach(System.out::println);
    }
}
