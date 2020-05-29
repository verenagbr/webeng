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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    private ShoppingManager shoppingManager = new ShoppingManager();
    private ArticleManager articleManager = new ArticleManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void generateHead(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("header.html");

        try {
            rd.include(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateFoot(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("footer.html");

        try {
            rd.include(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        generateHead(request, response);

        out.println("<h1>Praktikum 4 - Shopsystem mit 3-Schicht-Architektur</h1></br></br></br>");

        out.println("           <b>Artikelliste ausgeben.</b>");
        generateArticleList(out);

        out.println("           </br></br><b>F&uumlge 3 Artikel zum Warenkorb hinzu.</b></br>");
        addThreeArticle();

        out.println("           </br></br><b>Warenkorb ausgeben.</b>");
        generateShoppingcartlist(out);

        out.println("           </br></br><b>Artikelliste ausgeben nachdem Waren in den Warenkorb gelegt wurden.</b>");
        generateArticleList(out);

        out.println("           </br></br><b>F&aumlllige Summe.</b>");
        double toPay = shoppingManager.completeShoppingProcess();
        showSum(out, toPay);

        generateFoot(request, response);
    }

    private void showSum(PrintWriter out, double sum) {
        out.println("           </br>Bitte bezahlen sie <u>" + sum + "&euro;</u>");
    }

    private void addThreeArticle() {
        shoppingManager.addArticleToShoppingCart("183");
        shoppingManager.addArticleToShoppingCart("953");
        shoppingManager.addArticleToShoppingCart("984");
    }

    private void generateShoppingcartlist(PrintWriter out) {
        List<Article> shoppingcartlist = shoppingManager.getShoppingCart();
        double sum = shoppingManager.getTotal();


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
        for(Article article : shoppingcartlist) {
            out.println("           <tr>");
            out.println("               <td>");
            out.println("                   "+article.getUuid());
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
        out.println("                        <b><u>Summe: " + sum + "&euro;</u></b>");
        out.println("                   </td>");
        out.println("               </tr>");
        out.println("</table>");
    }

    private void generateArticleList(PrintWriter out) {
        List<Article> articlelist = articleManager.getArticleList();
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
        for(Article a : articlelist) {
            out.println("           <tr>");
            out.println("               <td>");
            out.println("                   "+a.getUuid());
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

