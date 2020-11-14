package kz.edu.astanait.servlets;

import kz.edu.astanait.api.client.StudentClient;
import kz.edu.astanait.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.BadRequestException;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        StudentClient studentClient = new StudentClient();
        Cookie errorCookie;
        try {
            Student student = studentClient.getByUsername(username);
            if (student.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", student);
                response.sendRedirect(getServletContext().getContextPath());
                return;
            } else {
                errorCookie = new Cookie("error", "Incorrect_password");
            }
        } catch (BadRequestException e) {
            errorCookie = new Cookie("error", e.getMessage().replaceAll(" ", "_"));
        }
        errorCookie.setMaxAge(5);
        response.addCookie(errorCookie);
        response.sendRedirect(request.getHeader("Referer"));
    }
}