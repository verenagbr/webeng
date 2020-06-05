<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="de.hsduesseldorf.webengshop.Article" %>
<%@ page import="java.util.ArrayList" %>

<%@include file="../header.html" %>
<%@include file="../nav.html" %>

<% Boolean success = (Boolean) request.getAttribute("success"); %>
<%@include file="../messages.jsp" %>

<% ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articleList"); %>
<table class="table table-striped table-light table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Preis</th>
        <th>Menge</th>
        <th>Bearbeiten L&ouml;schen</th>
    </tr>
    </thead>
    <tbody>
    <% for (Article article : articles) {%>
    <tr>
        <form method="get" action="./">
            <td>
                <input class="form-control" name="uuid" type="number" readonly value="<%=article.getUuid()%>">
            </td>
            <td>
                <input class="form-control" name="name" type="text" value="<%=article.getName()%>">
            </td>
            <td>
                <input class="form-control" name="price" type="number" value="<%=article.getPrice()%>">
            </td>
            <td>
                <input class="form-control" name="stock" type="number" value="<%=article.getStock()%>">
            </td>
            <td>
                <input name="action" type="hidden" value="admin">
                <input name="authToken" type="hidden" value="<%=request.getParameter("authToken")%>">
                <button type="submit" class="btn btn-link">
                    <i class="fas fa-pencil-alt"></i>
                </button>
                <a href="./?action=admin&authToken=<%=request.getParameter("authToken")%>&uuid=<%=article.getUuid()%>">
                    <i class="fas fa-trash-alt"></i>
                </a>
            </td>
        </form>
    </tr>
    <%}%>
    </tbody>
    <tfoot>
    <tr>
        <form method="get" action="./">
            <td>
                Neuer Artikel:
            </td>
            <td>
                <input class="form-control" name="name" type="text">
            </td>
            <td>
                <input class="form-control" name="price" type="number">
            </td>
            <td>
                <input class="form-control" name="stock" type="number">
            </td>
            <td>
                <input name="action" type="hidden" value="admin">
                <input name="authToken" type="hidden" value="<%=request.getParameter("authToken")%>">
                <button type="submit" class="btn btn-link">
                    <i class="fas fa-plus-circle"></i>
                </button>
            </td>
        </form>
    </tr>
    </tfoot>
</table>

<%@include file="../footer.html" %>
