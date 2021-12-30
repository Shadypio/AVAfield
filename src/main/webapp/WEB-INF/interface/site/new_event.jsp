<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crea un evento</title>
</head>
<body>
<%  LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");%>
<form action="${pageContext.request.contextPath}/ge/addEvento" method="post">
    <input type="hidden" name="idStruttura" value="<%=Integer.parseInt((String) request.getAttribute("idStruttura"))%>">
    <input type="text" name="nome" id="nome" placeholder="Nome" required>
    <input type="text" name="numeroPartecipanti" id="numeroPartecipanti" placeholder="Numero Partecipanti" required>
    <input type="date" name="dataEvento" id="dataEvento" placeholder="Data" value='<%=""+currentDate.format(formatter)%>' min='<%=""+currentDate.format(formatter)%>' max='2022-12-25' required>
    <input type="time" name="time" id="time" placeholder="Orario" required>
    <button type="submit" name="registrati">Salva</button>
    <button type="button" id='annulla'>Annulla</button>
</form>

</body>
</html>
