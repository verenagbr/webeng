package de.hsduesseldorf.webengshop.access;

import de.hsduesseldorf.webengshop.Article;

import java.util.List;


public interface ArticleDAO {

    Article get(int id);

    List<Article> getAll();

    void add(Article t);

    void update(Article t);

    void delete(Article t);
}
