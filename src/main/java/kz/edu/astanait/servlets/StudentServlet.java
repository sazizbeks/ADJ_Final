package kz.edu.astanait.servlets;

import com.google.gson.Gson;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.implementations.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

@WebServlet(name = "StudentServlet", urlPatterns = "/show/student")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        String inputVal = request.getParameter("inputVal");


        StudentRepository sr = new StudentRepository();
        response.setContentType("text/html");
        String studentJson = null;

        if(search.equalsIgnoreCase("showOne")){
            try {
                Stack<Student> student = new Stack<>();
                student.push(sr.findByUsername(inputVal));
                studentJson = new Gson().toJson(student);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            response.getWriter().write(studentJson);
            return;
        }

        else if(search.equalsIgnoreCase("showByGroup")){
            try {
                List<Student> student;
                student = sr.findByGroup(Integer.valueOf(inputVal));
                studentJson = new Gson().toJson(student);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            response.getWriter().write(studentJson);
            return;
        }

        else if(search.equalsIgnoreCase("showByMajor")){
            try {
                List<Student> student;
                student = sr.findByMajor(inputVal.toUpperCase());
                studentJson = new Gson().toJson(student);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            response.getWriter().write(studentJson);
            return;
        }

        else if(search.equalsIgnoreCase("showByYear")){
            try {
                List<Student> student;
                student = sr.findByYear(Integer.valueOf(inputVal));
                studentJson = new Gson().toJson(student);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            response.getWriter().write(studentJson);
            return;
        }
        else if(search.equalsIgnoreCase("showAll")){
            List<Student> student;
            student = sr.getAll();
            studentJson = new Gson().toJson(student);
            response.getWriter().write(studentJson);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
