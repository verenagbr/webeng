package de.hsduesseldorf.webengshop.logic;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.ShoppingCart;

import java.util.List;
import java.util.UUID;

public class ShoppingManager {

    private ShoppingCart shoppingCart = new ShoppingCart();

    private ArticleManager articleManager = new ArticleManager();

    public ArticleManager getArticleManager() {
        return articleManager;
    }

    public List<Article> getShoppingCart() {
        return shoppingCart.getArticles();
    }

    public float getTotal() {
        return shoppingCart.getTotal();
    }

    // Artikel im Einkaufswagen sollen aus der Datenbank entfernt werden (stock)
    public void addArticleToShoppingCart(String uuid) {
        Article articleToAdd = articleManager.getArticle(uuid);
        if (articleToAdd != null) {
            shoppingCart.addArticle(articleToAdd);
            articleManager.updateStock(articleToAdd, false);
        }
    }

    // entfernte  Artikel   müssen   wieder   in   die   Datenbank   eingefügt werden
    public void deleteArticleFromShoppingCart(String uuid) {
        Article articleToRemove = articleManager.getArticle(uuid);
        if (articleToRemove != null) {
            shoppingCart.removeArticle(articleToRemove);
            articleManager.updateStock(articleToRemove, true);
        }
    }

    // -> Einkaufswagen leeren
    public void cancelShoppingProcess() {
        if (!shoppingCart.isEmpty()) {
            List<Article> articles = shoppingCart.getArticles();
            for (Article article : articles) {
                deleteArticleFromShoppingCart(article.getUuid());
            }
            shoppingCart.clearShoppingCart();
        }
    }

    // Einkaufswagen leeren und Gesamtsumme einfordern
    public float completeShoppingProcess() {
        float total = 0f;
        if (!shoppingCart.isEmpty()) {
            total = shoppingCart.getTotal();
            shoppingCart.clearShoppingCart();
        }
        return total;
    }
}
