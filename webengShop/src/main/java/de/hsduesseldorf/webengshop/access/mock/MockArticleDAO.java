package de.hsduesseldorf.webengshop.access.mock;

import de.hsduesseldorf.webengshop.Article;
import de.hsduesseldorf.webengshop.access.ArticleDAO;

import java.util.ArrayList;
import java.util.List;

public class MockArticleDAO implements ArticleDAO {

    private static final List<Article> articles = new ArrayList<>();


    @Override
    public Article get(int uuid) {
        Article a = null;

        try {
            a = articles.get(uuid);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("MockArticleDAO::get: indexOutOfBounds");
        }

        return a;
    }

    @Override
    public List<Article> getAll() {
        return articles;
    }

    @Override
    public void add(Article article) {
        articles.add(article);
        article.setUuid(articles.size() - 1);
    }

    @Override
    public void update(Article article) {
        Article a = articles.get(article.getUuid());
        a.setName(article.getName());
        a.setPrice(article.getPrice());
        a.setStock(article.getStock());
    }

    @Override
    public void delete(Article article) {
        articles.remove(article);
    }
}
