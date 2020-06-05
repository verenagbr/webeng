<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="de.hsduesseldorf.webengshop.Article" %>

<%@include file="./header.html" %>
<%@include file="./nav.html" %>

<% Article article = (Article) request.getAttribute("article"); %>
<table class="table table-striped table-light table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Preis</th>
        <th>Menge</th>
    </tr>
    </thead>
    <tbody>
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
    </tr>
    </tbody>
</table>

<%-- TODO: Add to Shopping Cart --%>
<form class="form-inline" method="get" action="./">
    <div class="col-sm-1">
    </div>
    <div class="form-group row">
        <input id="action" name="action" type="hidden" value="shoppingcart">
        <input id="uuid" name="uuid" type="hidden" value="<%=article.getUuid()%>">
    </div>
    <div class="form-group row">
        <label for="amount" class="col-sm-2 col-form-label">Anzahl</label>
        <div class="col-sm-4">
            <input id="amount" <% if (article.getStock() == 0) { %>disabled<% }%> class="form-control" name="amount"
                   type="number" required>
        </div>
    </div>
    <button type="submit" <% if (article.getStock() == 0) { %>disabled<% }%> class="btn btn-link">
        <i class="fas fa-cart-plus"></i>
    </button>
</form>

<%@include file="./footer.html" %>
