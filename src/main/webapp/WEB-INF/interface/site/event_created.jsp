<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/success.css"/>" rel="stylesheet">
    <title>Evento Creato</title>
</head>
<body>
<div class="bg">
</div>
<div class="registeredtext">
    <h1>Grazie per averci scelto!</h1>
    <h3>Il tuo evento è stato creato con successo</h3>
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
    <a href="<%=request.getContextPath()%>/ge/listaPerPartecipare">Lista Eventi Aggiornata</a>
</div>
</body>
</html>
