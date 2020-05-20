
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Navigation", urlPatterns = {"/navigation"})
public class Navigation extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
                                                              IOException {
        response.getWriter().append(getNavigation());
    }

    private String getNavigation() {
        return new StringBuilder().append("<html><ul style=\"list-style: none;\">")
                                  .append(generateListItems(Webeng02.ACTION_HOME,
                                                            Webeng02.ACTION_HEADER,
                                                            Webeng02.ACTION_COOKIES,
                                                            Webeng02.ACTION_SEARCH))
                                  .append("</ul></html>").toString();
    }

    private String generateListItems(String... items) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (String item : items) {
            stringBuilder.append("<li style=\"display: inline;\"><a href='/webeng02/webeng02?action=")
                         .append(item)
                         .append("'>")
                         .append(item)
                         .append(" | </a></li>");
        }
        return stringBuilder.toString();
    }
}
