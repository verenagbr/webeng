package de.hsduesseldorf.webengshop.presentation.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            // Validator from web.xml
            String authTokenValidator = request.getServletContext().getInitParameter("AuthToken");
            // Value of query parameter authToken
            String authToken = request.getParameter("authToken");
            // Value of query parameter action
            String action = request.getParameter("action");

            // calls the next part of the filter chain if it is an admin and the token is correct
            if (action == null || !action.equals("admin")
                || authToken != null && authToken.equals(authTokenValidator)) {
                chain.doFilter(request, response);
            } else {
                response.sendError(403, "Zugriff nicht erlaubt!");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
