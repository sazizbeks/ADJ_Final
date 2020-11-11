package kz.edu.astanait.repositories.implementations;

import kz.edu.astanait.databases.IDB;
import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.interfaces.IStudentRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class StudentRepository implements IStudentRepository {

    private final IDB db = new Postgres();

    @Override
    public Student queryOne(String id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        List<Student> list = new LinkedList<>();

        try {
            String sql = "SELECT * FROM STUDENTS";
            Statement statement = db.getConnection().createStatement();
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
    public void add(Student entity) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Student entity) {

    }
}
