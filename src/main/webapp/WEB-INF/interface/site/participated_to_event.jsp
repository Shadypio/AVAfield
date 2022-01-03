<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/success.css"/>" rel="stylesheet">
    <title>Partecipazione</title>
</head>
<body>
<div class="bg">
</div>
<div class="registeredtext">
    <h1>Partecipazione accettata!</h1>
    <h3>Ti auguriamo buon divertimento</h3>
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
</div>
</body>
</html>
