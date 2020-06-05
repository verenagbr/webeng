package de.hsduesseldorf.webengshop.logic;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.ShoppingCart;

import java.util.Map;

public class ShoppingManager {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    private final ArticleManager articleManager = new ArticleManager();

    public ArticleManager getArticleManager() {
        return articleManager;
    }

    public Map<Article, Integer> getShoppingCart() {
        return shoppingCart.getArticles();
    }

    public float getTotal() {
        return shoppingCart.getTotal();
    }

    // Artikel im Einkaufswagen sollen aus der Datenbank entfernt werden (stock)
    public boolean addArticleToShoppingCart(int uuid) {
        return addArticleAmountToShoppingCart(uuid, 1);
    }

    public boolean addArticleAmountToShoppingCart(int uuid, int amount) {
        Article articleToAdd = articleManager.getArticle(uuid);
        if (articleToAdd != null
            && amount <= articleToAdd.getStock()
        ) {
            for (int i = 0; i < amount; i++) {
                shoppingCart.addArticle(articleToAdd);
                articleManager.updateStock(articleToAdd, false);
            }

            return true;
        }

        return false;
    }

    // entfernte  Artikel   müssen   wieder   in   die   Datenbank   eingefügt werden
    public void deleteArticleFromShoppingCart(int uuid) {
        Article articleToRemove = articleManager.getArticle(uuid);
        if (articleToRemove != null) {
            int amount = shoppingCart.getArticles().get(articleToRemove);
            for (int i = 0; i < amount; i++) {
                shoppingCart.removeArticle(articleToRemove);
                articleManager.updateStock(articleToRemove, true);
            }
        }
    }

    // -> Einkaufswagen leeren
    public void cancelShoppingProcess() {
        if (!shoppingCart.isEmpty()) {
            Map<Article, Integer> articles = shoppingCart.getArticles();
            for (Article article : articles.keySet()) {
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
