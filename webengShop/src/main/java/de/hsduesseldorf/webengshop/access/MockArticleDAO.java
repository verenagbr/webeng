package de.hsduesseldorf.webengshop.access;

import de.hsduesseldorf.webengshop.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MockArticleDAO {

    private List<Article> articles = new ArrayList<>(
            Arrays.asList(
                    new Article(UUID.randomUUID(), "Test Article 1", 1.34f, 0),
                    new Article(UUID.randomUUID(), "Test Article 2", 1.34f, 0),
                    new Article(UUID.randomUUID(), "Test Article 3", 1.34f, 4),
                    new Article(UUID.randomUUID(), "Test Article 4", 1.34f, 0)
                         )
    );

    Article getArticle(UUID uuid) {
        for (final Article article : articles) {
            if (article.getUuid().equals(uuid)) {
                return article;
            }
        }

        return null;
    }
}
