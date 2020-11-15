package kz.edu.astanait.servlets;

import kz.edu.astanait.api.client.StudentClient;
import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Admin;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.implementations.AdminRepository;

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
        AdminRepository ar = new AdminRepository();
        StudentClient studentClient = new StudentClient();
        Cookie errorCookie;
        try {
            if(password.equalsIgnoreCase("1234")){
                Admin admin = ar.login(username, password);
                HttpSession session = request.getSession();
                session.setAttribute("user", admin);
                session.setAttribute("admin", true);
                response.sendRedirect(getServletContext().getContextPath());
                return;
            }
            else {
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
        }catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}