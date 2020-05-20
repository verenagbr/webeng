import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet(name = "Webeng02", urlPatterns = {"/webeng02"})
public class Webeng02 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String ACTION_HOME = "home";
    static final String ACTION_HEADER = "header";
    static final String ACTION_COOKIES = "cookies";
    static final String ACTION_SEARCH = "search";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Webeng02() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        request.getRequestDispatcher("navigation").include(request, response);

        final String actionParameter = request.getParameter("action");
        if (actionParameter != null && !actionParameter.equals(ACTION_HOME)) {
            handleActionParameter(request, response, actionParameter);
        } else {
            response.getWriter().println("<html><h2>Willkommen Verena Gebauer (804970)</h2></html>");
        }
    }

    private void handleActionParameter(HttpServletRequest request,
                                       HttpServletResponse response,
                                       String actionParameter) throws IOException {
        switch (actionParameter) {
            case ACTION_HEADER:
                proceedHeaderAction(request, response);
                break;
            case ACTION_COOKIES:
                proceedCookiesAction(request, response);
                break;
            case ACTION_SEARCH:
                response.sendRedirect("https://www.google.com");
                break;
        }
    }

    private void proceedCookiesAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("<html>");
        for (Cookie cookie : request.getCookies()) {
            response.getWriter().append(getCookieInformation(cookie));
        }
        response.getWriter().append("</html>");
        Cookie cookie = new Cookie("myCookie", LocalDateTime.now().toString());
        cookie.setMaxAge(10);
        response.addCookie(cookie);
    }

    private void proceedHeaderAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final Enumeration<String> headerNames = request.getHeaderNames();
        response.getWriter().append("<html><table>");
        while (headerNames.hasMoreElements()) {
            response.getWriter().append(generateTableRow(request, headerNames.nextElement()));
        }
        response.getWriter().append("</table></html>");
    }

    private CharSequence generateTableRow(final HttpServletRequest request, final String headerName) {
        return new StringBuilder().append("<tr>")
                                  .append("<td>")
                                  .append(headerName)
                                  .append("</td>")
                                  .append("<td>")
                                  .append(request.getHeader(headerName))
                                  .append("</td>")
                                  .append("<td>")
                                  .append(getHeaderDescription(headerName))
                                  .append("</td>")
                                  .append("</tr>").toString();
    }

    private String getHeaderDescription(final String headerName) {
        if (headerName.equals("Accept")) {
            return "Advertises which content types, expressed as MIME types, the client is able to understand.";
        }

        if (headerName.equals("User-Agent")) {
            return "Is a characteristic string that lets servers and network peers identify the application, "
                   + "operating system, vendor, and/or version of the requesting user agent.";
        }

        return "";
    }

    private String getCookieInformation(final Cookie cookie) {
        return String.format("name: %s, maxAge: %d, domain: %s, path: %s, value: %s<br>",
                             cookie.getName(),
                             cookie.getMaxAge(),
                             cookie.getDomain(),
                             cookie.getPath(),
                             cookie.getValue()
                            );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        doGet(request, response);
    }

}
