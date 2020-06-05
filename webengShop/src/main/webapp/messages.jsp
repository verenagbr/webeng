<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Boolean message = (Boolean) request.getAttribute("message"); %>
<% String messageType = (String) request.getAttribute("messageType"); %>
<% String messageContent = (String) request.getAttribute("messageContent"); %>

<% if (message != null && message) {%>
<div class="alert alert-<%=messageType%>" role="alert">
    <%=messageContent%>
</div>
<%}%>
