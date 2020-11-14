package kz.edu.astanait.servlets;

import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.implementations.StudentRepository;
import kz.edu.astanait.repositories.interfaces.IStudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        IStudentRepository studentRepository = new StudentRepository();
        Cookie errorCookie;
        try {
            Student student = studentRepository.findByUsername(username);
            if (student.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", student);
                response.sendRedirect(getServletContext().getContextPath());
                return;
            } else {
                errorCookie = new Cookie("error", "Incorrect_password");
            }
        } catch (NotFoundException e) {
            errorCookie = new Cookie("error", e.getMessage().replaceAll(" ", "_"));
        }
        errorCookie.setMaxAge(5);
        response.addCookie(errorCookie);
        response.sendRedirect(request.getHeader("Referer"));
    }
}