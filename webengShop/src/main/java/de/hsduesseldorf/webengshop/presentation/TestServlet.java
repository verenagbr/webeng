package de.hsduesseldorf.webengshop.presentation;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.ShoppingCart;
import de.hsduesseldorf.webengshop.logic.ArticleManager;
import de.hsduesseldorf.webengshop.logic.ShoppingManager;

import javax.servlet.RequestDispatcher;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    private final ShoppingManager shoppingManager = new ShoppingManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Not implemented yet
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<h1>Praktikum 4 - Shopsystem mit 3-Schicht-Architektur</h1></br></br></br>");

        out.println("           <b>Artikelliste ausgeben.</b>");
        generateArticleList(out);

        out.println("           </br></br><b>F&uuml;ge 3 Artikel zum Warenkorb hinzu.</b></br>");
        addThreeArticle();

        out.println("           </br></br><b>Warenkorb ausgeben.</b>");
        generateShoppingcartlist(out);

        out.println("           </br></br><b>Artikelliste ausgeben nachdem Waren in den Warenkorb gelegt wurden.</b>");
        generateArticleList(out);

        out.println("           </br></br><b>F&auml;llige Summe.</b>");
        float toPay = shoppingManager.completeShoppingProcess();
        showSum(out, toPay);
    }

    private void showSum(PrintWriter out, float sum) {
        NumberFormat numberFormat = new DecimalFormat("0.00");
        numberFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        out.println("           </br>Bitte bezahlen sie <u>" + numberFormat.format(sum) + "&euro;</u>");
    }

    private void addThreeArticle() {
        shoppingManager.addArticleToShoppingCart("183");
        shoppingManager.addArticleToShoppingCart("953");
        shoppingManager.addArticleToShoppingCart("984");
    }

    private void generateShoppingcartlist(PrintWriter out) {
        List<Article> shoppingcartlist = shoppingManager.getShoppingCart();
        NumberFormat numberFormat = new DecimalFormat("0.00");
        numberFormat.setRoundingMode(RoundingMode.HALF_EVEN);


        out.println("</br>");
        out.println("       <table>");
        out.println("           <tr>");
        out.println("               <th>");
        out.println("                   ID");
        out.println("               </th>");
        out.println("               <th>");
        out.println("                   Name");
        out.println("               </th>");
        out.println("               <th>");
        out.println("                   Preis");
        out.println("               </th>");
        out.println("               <th>");
        out.println("                   Menge");
        out.println("               </th>");
        out.println("           </tr>");
        for (Article article : shoppingcartlist) {
            out.println("           <tr>");
            out.println("               <td>");
            out.println("                   " + article.getUuid());
            out.println("               </td>");
            out.println("               <td>");
            out.println("                   " + article.getName());
            out.println("               </td>");
            out.println("               <td>");
            out.println("                   " + article.getPrice() + "&euro;");
            out.println("               </td>");
            out.println("           </tr>");
        }
        out.println("               <tr>");
        out.println("                   <td colspan= '4' style='text-align:right'>");
        out.println("                        <b><u>Summe: " + numberFormat.format(shoppingManager.getTotal())
                    + "&euro;</u></b>");
        out.println("                   </td>");
        out.println("               </tr>");
        out.println("</table>");
    }

    private void generateArticleList(PrintWriter out) {
        ArticleManager articleManager = shoppingManager.getArticleManager();
        List<Article> articleList = articleManager.getArticleList();
        out.println("</br>");
        out.println("       <table>");
        out.println("           <tr>");
        out.println("               <th>");
        out.println("                   ID");
        out.println("               </th>");
        out.println("               <th>");
        out.println("                   Name");
        out.println("               </th>");
        out.println("               <th>");
        out.println("                   Preis");
        out.println("               </th>");
        out.println("               <th>");
        out.println("                   Menge");
        out.println("               </th>");
        out.println("           </tr>");
        for (Article a : articleList) {
            out.println("           <tr>");
            out.println("               <td>");
            out.println("                   " + a.getUuid());
            out.println("               </td>");
            out.println("               <td>");
            out.println("                   " + a.getName());
            out.println("               </td>");
            out.println("               <td>");
            out.println("                   " + a.getPrice());
            out.println("               </td>");
            out.println("               <td>");
            out.println("                   " + a.getStock());
            out.println("               </td>");
            out.println("           </tr>");
        }
        out.println("</table>");

    }
}

