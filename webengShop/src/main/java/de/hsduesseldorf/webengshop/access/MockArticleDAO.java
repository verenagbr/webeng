package de.hsduesseldorf.webengshop.access;

import de.hsduesseldorf.webengshop.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockArticleDAO {

    private List<Article> articles = new ArrayList<>(
            Arrays.asList(
                    new Article("183", "Test Article 1", 1.34f, 32),
                    new Article("953", "Test Article 2", 5.00f, 13),
                    new Article("984", "Test Article 3", 0.99f, 1),
                    new Article("124", "Test Article 4", 12.99f, 0)
                         )
    );

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(String uuid) {
        for (final Article article : articles) {
            if (article.getUuid().equals(uuid)) {
                return article;
            }
        }

        return null;
    }

    /**
     * removes an article from the database
     *
     * @param uuid id of the article to remove
     */
    public void removeArticle(String uuid) {
        Article articleToRemove = getArticle(uuid);
        int stock = articleToRemove.getStock();
        articleToRemove.setStock(stock - 1);
    }

    /**
     * adds an article to the database
     *
     * @param uuid id of the article to add
     */
    public void addArticle(String uuid) {
        Article articleToAdd = getArticle(uuid);
        int stock = articleToAdd.getStock();
        articleToAdd.setStock(stock + 1);
    }
}
