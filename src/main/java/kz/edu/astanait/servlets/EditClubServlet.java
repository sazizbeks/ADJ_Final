package kz.edu.astanait.servlets;

import kz.edu.astanait.models.Club;
import kz.edu.astanait.repositories.implementations.ClubRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditServlet", urlPatterns = "/edit/club")
public class EditClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("club_Id"));
        String clubName = request.getParameter("club_name");
        Club club = new Club.Builder()
                .setClub_id(id)
                .setClub_name(clubName).build();
        ClubRepository cr = new ClubRepository();
        cr.update(club);
        response.sendRedirect(getServletContext().getContextPath() + "/jsp/club.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
