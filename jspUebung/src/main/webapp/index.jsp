<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<h1>Aufgabe 2.1 a)</h1>
<p>Aktuelles Datum:
    <%=new java.text.SimpleDateFormat().format(new java.util.Date()) %>
</p>
<p>3 x 5 =
    <%=3 * 5 %>
</p>

<h1>Aufgabe 2.1 b)</h1>
<%!
    public String calculateCaesarCipher(String codeWort, String verschiebung) {
        StringBuilder result = new StringBuilder();
        if (codeWort != null && !codeWort.isEmpty()
            && verschiebung != null && !verschiebung.isEmpty()) {
            int offset = Integer.parseInt(verschiebung);
            for (char character : codeWort.toCharArray()) {
                if (Character.isWhitespace(character)) {
                    result.append(character);
                } else {
                    int originalAlphabetPosition = character - 'a';
                    int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                    char newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(newCharacter);
                }
            }
        } else {
            StringBuilder error = new StringBuilder();

            if (codeWort != null && codeWort.isEmpty()) {
                error.append("Es muss ein Codewort angegeben werden!");
            }
            if (verschiebung != null && verschiebung.isEmpty()) {
                error.append("Es muss eine Verschiebung angegeben werden!");
            }

            return error.toString();
        }

        return result.toString();
    }
%>

<form method="get" action="index.jsp">
    <label for="codeWort">Codewort:</label>
    <input id="codeWort" name="codeWort" type="text">
    <label for="verschiebung">Verschiebung:</label>
    <input id="verschiebung" name="verschiebung" type="number">
    <button type="submit">Anwenden</button>
</form>
<p>
    <%=calculateCaesarCipher(request.getParameter("codeWort"), request.getParameter("verschiebung"))%>
</p>

<h1>Aufgabe 2.1 c)</h1>

<p>
    <%=request.getMethod()%>
</p>

<p>
    <%=config.getServletContext().getInitParameter("parameter")%>
</p>

</body>
</html>
