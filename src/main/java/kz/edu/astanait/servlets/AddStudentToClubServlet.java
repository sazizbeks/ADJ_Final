package kz.edu.astanait.servlets;

import kz.edu.astanait.repositories.implementations.ClubRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddStudentToClubServlet", urlPatterns = "/add/student/to/club")
public class AddStudentToClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clubId = request.getParameter("cludId");
        String studentId = request.getParameter("studentId");

        ClubRepository cr = new ClubRepository();
        cr.addStudentToClub(Integer.valueOf(clubId), Integer.valueOf(studentId));
        response.sendRedirect(getServletContext().getContextPath()+"/jsp/club.jsp");
        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
