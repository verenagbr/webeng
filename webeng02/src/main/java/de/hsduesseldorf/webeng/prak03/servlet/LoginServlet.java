package de.hsduesseldorf.webeng.prak03.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usernameLabel = "username";
        String username = request.getParameter(usernameLabel);
        if (username.isEmpty()){
            response.sendRedirect("401.html");
        } else {
            request.getSession(true).setAttribute(usernameLabel, username);
            response.sendRedirect("protected/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: not implemented yet
    }
}
