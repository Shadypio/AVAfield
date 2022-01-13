<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/success.css"/>" rel="stylesheet">
    <title>Registrazione Effettuata</title>
    <link rel="icon" href="../images/logo_no_text.png" type="image/gif"/>
</head>
<body>
<div class="bg">
</div>
<div class="registeredtext">
    <h1>Ti diamo il benvenuto!</h1>
    <h3>Registrazione effettuata con successo.</h3>
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
</div>
</body>
</html>