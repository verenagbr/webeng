<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="de.hsduesseldorf.webengshop.Article" %>
<%@ page import="java.util.Map" %>

<%@include file="./header.html" %>
<%@include file="./nav.html" %>

<%@include file="./messages.jsp" %>

<% Map<Article, Integer> articles = (Map<Article, Integer>) request.getAttribute("shoppingCartList"); %>
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
    <% for (Article article : articles.keySet()) {%>
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
            <%=articles.get(article)%>
        </td>
        <td>
            <a href="./?action=articledetails&uuid=<%=article.getUuid()%>"><i class="fas fa-info-circle"></i></a>
            <a href="./?action=shoppingcart&uuid=<%=article.getUuid()%>&amount=-<%=articles.get(article)%>">
                <i class="fas fa-trash-alt"></i>
            </a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>

<form class="form-inline" method="get" action="./">
    <div class="col-sm-1">
    </div>
    <div class="form-group row">
        <input id="action" name="action" type="hidden" value="checkout">
    </div>
    <button type="submit" <% if (articles.isEmpty()) { %>disabled<% }%> class="btn btn-link">
        Einkauf Abschlie&szlig;en
    </button>
</form>

<%@include file="./footer.html" %>
