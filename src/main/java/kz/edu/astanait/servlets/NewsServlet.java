package kz.edu.astanait.servlets;

import kz.edu.astanait.api.client.NewsClient;
import kz.edu.astanait.models.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsServlet", urlPatterns = "/news")
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnVal = request.getParameter("btnVal");
        NewsClient client = new NewsClient();
        int id = Integer.parseInt(request.getParameter("id"));
        if (btnVal.equals("EDIT")) {
            String title = request.getParameter("title");
            String desc = request.getParameter("description");
            News news = new News.Builder()
                    .setId(id)
                    .setTitle(title)
                    .setDescription(desc)
                    .build();
            client.update(news);
        } else if (btnVal.equals("delete")) {
            News news = new News.Builder()
                    .setId(id)
                    .build();
            client.delete(news);
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