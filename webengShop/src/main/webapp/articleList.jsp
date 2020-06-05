<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="de.hsduesseldorf.webengshop.Article" %>
<%@ page import="java.util.ArrayList" %>

<%@include file="./header.html" %>
<%@include file="./nav.html" %>

<% ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articleList"); %>
<table class="table table-striped table-light table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Preis</th>
        <th>Menge</th>
        <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <% for (Article article : articles) {%>
    <tr>
        <td>
            <%=article.getUuid()%>
        </td>
        <td>
            <%=article.getName()%>
        </td>
        <td>
            <%=article.getPrice()%>
        </td>
        <td>
            <%=article.getStock()%>
        </td>
        <td>
            <a href="./?action=articledetails&uuid=<%=article.getUuid()%>"><i class="fas fa-info-circle"></i></a>
            <a href="./?action=shoppingcart&uuid=<%=article.getUuid()%>&amount=1">
                <i class="fas fa-cart-plus"></i>
            </a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>

<%@include file="./footer.html" %>
