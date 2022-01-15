<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/errors.css"/>" rel="stylesheet">
    <title>Partecipazione evento</title>
    <link rel="icon" href="../images/logo_no_text.png" type="image/gif"/>
</head>
<body>
<div class="bg">
</div>
<div class="errortext">
    <h1>Qualcosa è andato storto</h1>
    <h3>Sei già prenotato a questo evento</h3>
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
</div>
</body>
</html>