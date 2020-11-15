package kz.edu.astanait.repositories.interfaces;

import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Student;

import java.util.List;

public interface IStudentRepository extends IRetrieve<Student> {
    Student findById(Integer id) throws NotFoundException;
    Student findByUsername(String username) throws NotFoundException;
    List<Student> findByGroup(Integer group_num) throws NotFoundException;
    List<Student> findByMajor(String major) throws NotFoundException;
    List<Student> findByYear(Integer year) throws NotFoundException;
    List<Student> findByFName(String fname) throws NotFoundException;
    List<Student> findByLName(String lname) throws NotFoundException;
}
