package de.hsduesseldorf.webengshop.logic;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.access.MockArticleDAO;

import java.util.List;

public class ArticleManager {
    private MockArticleDAO articleDB = new MockArticleDAO();

    public List<Article> getArticleList() {
        return articleDB.getArticles();
    }

    public Article getArticle(String uuid) {
        return articleDB.getArticle(uuid);
    }

    /**
     * Updates the stock of an Article
     *
     * @param article          article to update
     * @param increaseStock true if stock should be increased, false if stock should be decreased
     */
    public void updateStock(Article article, Boolean increaseStock) {
        if (increaseStock) {
            articleDB.addArticle(article.getUuid());
            article.setStock(article.getStock() + 1);
        } else {
            articleDB.removeArticle(article.getUuid());
            article.setStock(article.getStock() - 1);
        }
    }
}
