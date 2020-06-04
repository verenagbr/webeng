<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<p>Aktuelles Datum:
    <%=new java.text.SimpleDateFormat().format(new java.util.Date()) %>
</p>
<p>3 x 5 =
    <%=3 * 5 %>
</p>
</body>
</html>
