package kz.edu.astanait.servlets;

import kz.edu.astanait.databases.Postgres;
import kz.edu.astanait.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "AddParticipantToEvent", urlPatterns = "/add/event")
public class AddParticipantToEvent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int p_id = ((Student) session.getValue("user")).getId();
        int e_id = Integer.parseInt(request.getParameter("id"));

        try {
            Statement statement = Postgres.getConnection().createStatement();
            statement.executeQuery("INSERT INTO events_students VALUES (" + e_id + "," + p_id + ")");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        response.sendRedirect(getServletContext().getContextPath()+"/jsp/event.jsp");
    }
}
