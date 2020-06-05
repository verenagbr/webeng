package de.hsduesseldorf.webengshop.presentation;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.logic.ArticleManager;
import de.hsduesseldorf.webengshop.logic.ShoppingManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    private final ShoppingManager shoppingManager = new ShoppingManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Not implemented yet
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArticleManager articleManager = shoppingManager.getArticleManager();
        PrintWriter out = response.getWriter();

        out.println("<h1>Praktikum 4 - Shopsystem mit 3-Schicht-Architektur</h1>");
        generateArticleList(out, "Artikelliste", articleManager.getArticleList());
        addThreeArticle(out);
        generateShoppingCartList(out);
        generateArticleList(out,
                            "Artikelliste nachdem Waren in den Warenkorb gelegt wurden",
                            articleManager.getArticleList());
        showSum(out);
    }

    private void generateArticleList(PrintWriter out, final String headline, Collection<Article> articles) {
        out.printf("<h2>%s</h2>%n", headline);
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Preis</th>");
        out.println("<th>Menge</th>");
        out.println("</tr>");
        for (Article article : articles) {
            out.println("<tr>");
            out.printf("<td>%d</td>%n", article.getUuid());
            out.printf("<td>%s</td>%n", article.getName());
            out.printf("<td>%s</td>%n", article.getPrice());
            out.printf("<td>%d</td>%n", article.getStock());
            out.println("</tr>");
        }
        out.println("</table>");

    }

    private void addThreeArticle(final PrintWriter out) {
        out.println("<h2>F&uuml;ge 3 Artikel zum Warenkorb hinzu</h2>");
        out.println(shoppingManager.addArticleToShoppingCart(0));
        out.println(shoppingManager.addArticleToShoppingCart(1));
        out.println(shoppingManager.addArticleToShoppingCart(3));
    }

    private void generateShoppingCartList(PrintWriter out) {
        NumberFormat numberFormat = new DecimalFormat("0.00");
        numberFormat.setRoundingMode(RoundingMode.HALF_EVEN);

        generateArticleList(out, "Warenkorb", shoppingManager.getShoppingCart().keySet());
        out.printf("Summe: %s%n", numberFormat.format(shoppingManager.getTotal()));
    }

    private void showSum(PrintWriter out) {
        NumberFormat numberFormat = new DecimalFormat("0.00");
        numberFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        String sum = numberFormat.format(shoppingManager.completeShoppingProcess());

        out.println("<h2>F&auml;llige Summe</h2>");
        out.printf("Bitte bezahlen sie <u>%s&euro;</u>%n", sum);
    }
}

