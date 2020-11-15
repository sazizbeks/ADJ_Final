package kz.edu.astanait.servlets;

import kz.edu.astanait.api.client.EventClient;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EventServlet", urlPatterns = "/event")
public class EventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnVal = request.getParameter("btnVal");

        HttpSession session = request.getSession();
        EventClient client = new EventClient();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        switch (btnVal) {
            case "Add": {
                int creator_id = ((Student) session.getValue("user")).getId();
                String name = request.getParameter("name");
                Date start_date = null;
                Date end_date = null;
                try {
                    start_date = format.parse(request.getParameter("start_date").replaceAll("T", " "));
                    end_date = format.parse(request.getParameter("end_date").replaceAll("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Event event = new Event.Builder()
                        .setName(name)
                        .setStart_date(start_date)
                        .setEnd_date(end_date)
                        .setCreator_id(creator_id)
                        .build();
                client.add(event);
                break;
            }
            case "EDIT": {
                int creator_id = ((Student) session.getValue("user")).getId();
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                Date start_date = null;
                Date end_date = null;
                try {
                    start_date = format.parse(request.getParameter("start_date").replaceAll("T", " "));
                    end_date = format.parse(request.getParameter("end_date").replaceAll("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Event event = new Event.Builder()
                        .setId(id)
                        .setName(name)
                        .setStart_date(start_date)
                        .setEnd_date(end_date)
                        .setCreator_id(creator_id)
                        .build();
                client.update(event);
                break;
            }
            case "delete":{
                int id = Integer.parseInt(request.getParameter("id"));
                client.delete(
                        new Event.Builder()
                        .setId(id)
                        .build()
                );
            }
        }
        response.sendRedirect(getServletContext().getContextPath() + "/jsp/studentEvents.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnVal = request.getParameter("btnVal");
        switch (btnVal) {
            case "Edit":
                String id = request.getParameter("eventId");
                Cookie cookie = new Cookie("eventId", id);
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                response.sendRedirect(getServletContext().getContextPath() + "/jsp/editEvent.jsp");
                break;
        }
    }
}
