package kz.edu.astanait.servlets;

import kz.edu.astanait.api.client.NewsClient;
import kz.edu.astanait.models.News;
import kz.edu.astanait.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "NewsServlet", urlPatterns = "/news")
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnVal = request.getParameter("btnVal");
        NewsClient client = new NewsClient();

        switch (btnVal) {
            case "EDIT": {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String desc = request.getParameter("description");
                News news = new News.Builder()
                        .setId(id)
                        .setTitle(title)
                        .setDescription(desc)
                        .build();
                client.update(news);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                News news = new News.Builder()
                        .setId(id)
                        .build();
                client.delete(news);
                break;
            }
            case "ADD":
                String title = request.getParameter("title");
                String desc = request.getParameter("description");
                HttpSession session = request.getSession();
                client.add(
                        new News.Builder()
                                .setTitle(title)
                                .setDescription(desc)
                                .setModerator_id(((Student) session.getValue("user")).getId())
                                .build()
                );
                break;
        }
        response.sendRedirect(getServletContext().getContextPath() + "/jsp/studentNews.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnVal = request.getParameter("btnVal");
        switch (btnVal) {
            case "all":
                response.sendRedirect(getServletContext().getContextPath() + "/jsp/news.jsp");
                break;
            case "myNews":
                response.sendRedirect(getServletContext().getContextPath() + "/jsp/studentNews.jsp");
                break;
            case "edit":
                String id = request.getParameter("newsId");
                Cookie cookie = new Cookie("newsId", id);
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                response.sendRedirect(getServletContext().getContextPath() + "/jsp/editNews.jsp");
                break;
            case "delete":
                break;
        }
    }
}