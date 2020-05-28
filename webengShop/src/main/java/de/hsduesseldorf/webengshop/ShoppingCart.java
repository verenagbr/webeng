package de.hsduesseldorf.webengshop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Article> articles = new ArrayList<>();
    private int total = 0;

    public List<Article> getArticles() {
        return articles;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(final int total) {
        this.total = total;
    }
}
