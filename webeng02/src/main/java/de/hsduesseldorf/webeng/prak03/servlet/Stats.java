package de.hsduesseldorf.webeng.prak03.servlet;

import de.hsduesseldorf.webeng.prak03.listener.MyListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Stats", urlPatterns = {"/protected/stats"})
public class Stats extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append(getStatistics(request));
    }

    private String getStatistics(HttpServletRequest request) {
        return new StringBuilder().append("<html lang=\"en\"><h3>Registered users:")
                                  .append(MyListener.getTotalActiveSession())
                                  .append("</h3><h3>User:")
                                  .append(request.getSession(false).getAttribute("username"))
                                  .append("</h3><br>")
                                  .append("<a href='index.html'>back</a>")
                                  .append("</html>").toString();
    }
}
