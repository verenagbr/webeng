package de.hsduesseldorf.webengshop;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    private final List<Article> articles = new LinkedList<>();

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article) {
        articles.add(article);
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

        for (final Article article : articles) {
            total += article.getPrice();
        }

        return total;
    }
}
