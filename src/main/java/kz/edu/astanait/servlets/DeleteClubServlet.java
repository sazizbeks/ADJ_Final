package kz.edu.astanait.servlets;

import kz.edu.astanait.models.Club;
import kz.edu.astanait.repositories.implementations.ClubRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteClubServlet", urlPatterns = "/delete/club")
public class DeleteClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("club_id");
        Club club = new Club(Integer.parseInt(id));
        ClubRepository cr = new ClubRepository();
        cr.delete(club);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
