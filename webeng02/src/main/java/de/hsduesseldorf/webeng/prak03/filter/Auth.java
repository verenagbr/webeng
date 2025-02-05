package de.hsduesseldorf.webeng.prak03.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Auth", urlPatterns = {"/protected/*"})
public class Auth implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpSession session = request.getSession(false);
            boolean loggedIn = session != null && session.getAttribute("username") != null;

            if (loggedIn) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/403.html");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
