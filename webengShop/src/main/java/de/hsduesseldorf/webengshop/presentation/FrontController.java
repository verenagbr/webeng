package de.hsduesseldorf.webengshop.presentation;

import de.hsduesseldorf.webengshop.logic.ArticleManager;
import de.hsduesseldorf.webengshop.logic.ShoppingManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = {"/"})
public class FrontController extends HttpServlet {
    private final ShoppingManager shoppingManager = new ShoppingManager();
    private final ArticleManager articleManager = shoppingManager.getArticleManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Not Implemented yet!!!
    }

    /*
     forwarding depending on action-parameter
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        String target;
        switch (action) {
            case "admin":
                target = processAdmin(request, response);
                break;
            case "articlelist":
                target = processArticleList(request);
                break;
            case "articledetails":
                target = processArticleDetails(request, response);
                break;
            case "shoppingcart":
                target = processShoppingCart(request, response);
                break;
            case "checkout":
                target = processCheckout();
                break;
            default:
                target = "index.jsp";
                break;
        }

        request.getRequestDispatcher(target).include(request, response);
    }

    /*
    forwarding to admin page
     */
    private String processAdmin(HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        processAdministrativeActions(request);

        request.setAttribute("articleList", articleManager.getArticleList());
        return "admin/admin.jsp";
    }

    /*
    Handling of administrative actions (update, add & delete atricles)
     */
    private void processAdministrativeActions(final HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        if (uuid != null && name != null && price != null && stock != null) {
            articleManager.updateArticle(Integer.parseInt(uuid),
                                         name,
                                         Float.parseFloat(price),
                                         Integer.parseInt(stock));
            prepareMessage(request, "success", "Artikel aktualisiert!");
        } else if (name != null && price != null && stock != null) {
            articleManager.addArticle(name, Float.parseFloat(price), Integer.parseInt(stock));
            prepareMessage(request, "success", "Artikel hinzugef&uuml;gt!");
        } else if (uuid != null && name == null && price == null && stock == null) {
            articleManager.deleteArticle(Integer.parseInt(uuid));
            prepareMessage(request, "success", "Artikel gel&ouml;scht!");
        }
    }

    /*
    creates a message (sucess)
     */
    private void prepareMessage(final HttpServletRequest request, final String type, final String message) {
        request.setAttribute("message", true);
        request.setAttribute("messageType", type);
        request.setAttribute("messageContent", message);
    }

    /*
    forwarding to article list page
     */
    private String processArticleList(HttpServletRequest request) {
        request.setAttribute("articleList", articleManager.getArticleList());
        return "articleList.jsp";
    }

    /*
    forwarding to article details page if article with the given id exists
     */
    private String processArticleDetails(final HttpServletRequest request,
                                         final HttpServletResponse response) throws IOException {
        String uuidString = request.getParameter("uuid");
        if (uuidString != null) {
            int uuid = Integer.parseInt(uuidString);
            if (uuid <= articleManager.getArticleList().size()) {
                request.setAttribute("article", articleManager.getArticle(uuid));
                return "articleDetails.jsp";
            }
        }

        response.sendError(404, "Artikel konnte nicht gefunden werden!");
        return "./";
    }

    /*
    updating of the shopping cart & forwarding to the (updated) shopping cart page
     */
    private String processShoppingCart(HttpServletRequest request,
                                       final HttpServletResponse response) throws IOException {
        String uuidParam = request.getParameter("uuid");
        String amountParam = request.getParameter("amount");
        if (uuidParam != null && amountParam != null) {
            int amount = Integer.parseInt(amountParam);
            int uuid = Integer.parseInt(uuidParam);
            updateShoppingCart(request, response, amount, uuid);
        }

        request.setAttribute("shoppingCartList", shoppingManager.getShoppingCart());
        return "shoppingCart.jsp";
    }

    /*
    adding an article to the shopping cart if the given amount is greater than 0
     */
    private void updateShoppingCart(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final int amount,
                                    final int uuid) throws IOException {
        if (amount > 0) {
            if (!shoppingManager.addArticleAmountToShoppingCart(uuid,
                                                                amount)) {
                response.sendError(400, "UUID oder Anzahl waren nicht korrekt!");
            }
        } else if (amount < 0) {
            System.out.println("Should delete: " + amount);
            shoppingManager.deleteArticleFromShoppingCart(uuid);
            prepareMessage(request, "success", "Aus Warenkorb entfernt!");
        }
    }

    /*
    forwarding to the thanks page
     */
    private String processCheckout() {
        shoppingManager.completeShoppingProcess();
        return "thanks.jsp";
    }
}
