package de.hsduesseldorf.webengshop;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Article, Integer> articles = new HashMap<>();

    public Map<Article, Integer> getArticles() {
        return articles;
    }

    public void addArticle(Article article) {
        articles.compute(article,
                         (Article key, Integer amount) -> amount != null ? amount + 1 : 1);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    public Boolean isEmpty() {
        return articles.isEmpty();
    }

    public void clearShoppingCart() {
        articles.clear();
    }

    public float getTotal() {
        float total = 0f;

        for (final Article article : articles.keySet()) {
            total += article.getPrice();
        }

        return total;
    }
}
