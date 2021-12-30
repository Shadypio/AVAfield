<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: Enzuc
  Date: 30/12/2021
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crea un evento</title>
</head>
<body>
<%
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");%>
<form action="${pageContext.request.contextPath}/ge/addEventoUtente" method="post">
    <input type="text" name="nome" id="nome" placeholder="Nome" required>
    <input type="text" name="numeroPartecipanti" id="numeroPartecipanti" placeholder="Numero Partecipanti" required>
    <input type="date" name="dataEvento" id="dataEvento" placeholder="Data" value='<%=""+currentDate.format(formatter)%>' min='<%=""+currentDate.format(formatter)%>' max='2022-12-25' required>
    <input type="time" name="time" id="time" placeholder="Orario" required>
    <button type="submit" name="registrati">Salva</button>
    <button type="button" id='annulla'>Annulla</button>
</form>

</body>
</html>
