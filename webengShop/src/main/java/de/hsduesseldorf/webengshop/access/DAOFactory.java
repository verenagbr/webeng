package de.hsduesseldorf.webengshop.access;

import de.hsduesseldorf.webengshop.access.mock.MockArticleDAO;

public class DAOFactory {
    public static ArticleDAO getArticleDAO() {
        return new MockArticleDAO();
    }
}
