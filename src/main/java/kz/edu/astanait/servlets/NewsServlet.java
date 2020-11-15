package kz.edu.astanait.servlets;

import kz.edu.astanait.api.client.NewsClient;
import kz.edu.astanait.models.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsServlet", urlPatterns = "/news")
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnVal = request.getParameter("btnVal");

        NewsClient newsClient = new NewsClient();

        if (btnVal.equals("myNews")) {
            News[] news = newsClient.getAll();
            request.setAttribute("myNews", news);
            request.getRequestDispatcher(getServletContext().getContextPath() + "/jsp/studentNews.jsp").forward(request, response);
        }
    }
}