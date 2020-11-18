package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.interfaces.IStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {

    @Override
    public Student queryOne(String sql) throws NotFoundException {
        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new Student.Builder()
                        .setId(rs.getInt("student_id"))
                        .setFname(rs.getString("student_fname"))
                        .setLname(rs.getString("student_lname"))
                        .setYear(rs.getInt("year"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setMajor_id(rs.getString("major_id"))
                        .setGroup_number(rs.getInt("group_number"))
                        .build();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Student with such id has not found.");
    }

    @Override
    public List<Student> findSeveral(String sql) {
        List<Student> list = new ArrayList<>();

        try {
            Statement statement = Postgres.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new Student.Builder()
                                .setId(rs.getInt("student_id"))
                                .setFname(rs.getString("student_fname"))
                                .setLname(rs.getString("student_lname"))
                                .setYear(rs.getInt("year"))
                                .setUsername(rs.getString("username"))
                                .setPassword(rs.getString("password"))
                                .setMajor_id(rs.getString("major_id"))
                                .setGroup_number(rs.getInt("group_number"))
                                .build()
                );
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM STUDENTS";
        return findSeveral(sql);
    }

    @Override
    public Student findById(Integer id) throws NotFoundException {
        String sql = "SELECT * FROM STUDENTS WHERE student_id=" + id;
        return queryOne(sql);
    }

    @Override
    public Student findByUsername(String username) throws NotFoundException {
        String sql = "SELECT * FROM STUDENTS WHERE username=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            return queryOne(ps.toString());
        } catch (NotFoundException e) {
            throw new NotFoundException("Student with such username has not found.");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("Student with such username has not found.");
    }

    @Override
    public List<Student> findByGroup(Integer group_num) throws NotFoundException {
        String sql = "SELECT * FROM students WHERE  group_number=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setInt(1, group_num);
            return findSeveral(ps.toString());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("No student has found.");
    }

    @Override
    public List<Student> findByMajor(String major) throws NotFoundException {
        String sql = "SELECT * FROM students WHERE major_id = ? ";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1,major);
            return findSeveral(ps.toString());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("No student has found with such major.");
    }

    @Override
    public List<Student> findByYear(Integer year) throws NotFoundException {
        String sql = "SELECT * FROM students WHERE year=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setInt(1, year);
            return findSeveral(ps.toString());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("No student has found with such year.");
    }

    @Override
    public List<Student> findByFName(String fname) throws NotFoundException {
        String sql = "SELECT * FROM students WHERE student_fname=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, fname);
            return findSeveral(ps.toString());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("No student has found with such first name.");
    }

    @Override
    public List<Student> findByLName(String lname) throws NotFoundException {
        String sql = "SELECT * FROM students WHERE student_lname=?";
        try {
            PreparedStatement ps = Postgres.getConnection().prepareStatement(sql);
            ps.setString(1, lname);
            return findSeveral(ps.toString());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new NotFoundException("No student has found with such last name.");
    }
}
