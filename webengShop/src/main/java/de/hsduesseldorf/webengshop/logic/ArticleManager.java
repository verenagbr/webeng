package de.hsduesseldorf.webengshop.logic;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.access.ArticleDAO;
import de.hsduesseldorf.webengshop.access.DAOFactory;

import java.util.List;

public class ArticleManager {
    private final ArticleDAO articleDAO = DAOFactory.getArticleDAO();

    public ArticleManager() {
        articleDAO.add(new Article("TestArtikel01", 10, 7));
        articleDAO.add(new Article("TestArtikel02", 20, 6));
        articleDAO.add(new Article("TestArtikel03", 30, 5));
        articleDAO.add(new Article("TestArtikel04", 40, 4));
        articleDAO.add(new Article("TestArtikel05", 50, 3));
        articleDAO.add(new Article("TestArtikel06", 60, 2));
    }

    public List<Article> getArticleList() {
        return articleDAO.getAll();
    }

    public Article getArticle(int uuid) {
        return articleDAO.get(uuid);
    }

    public void addArticle(final String name, final float price, final int stock) {
        articleDAO.add(new Article(name, price, stock));
    }

    public void updateArticle(final int uuid, final String name, final float price, final int stock) {
        Article article = getArticle(uuid);
        article.setName(name);
        article.setPrice(price);
        article.setStock(stock);
    }

    public void deleteArticle(int uuid) {
        articleDAO.delete(getArticle(uuid));
    }

    /**
     * Updates the stock of an Article
     *
     * @param article       article to update
     * @param increaseStock true if stock should be increased, false if stock should be decreased
     */
    public void updateStock(Article article, Boolean increaseStock) {
        if (increaseStock) {
            article.setStock(article.getStock() + 1);
        } else {
            if (article.getStock() > 0) {
                article.setStock(article.getStock() - 1);
            }
        }
    }
}
