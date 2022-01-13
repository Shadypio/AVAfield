<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/css/errors.css"/>" rel="stylesheet">
    <title>Errore</title>
    <link rel="icon" href="../images/logo_no_text.png" type="image/gif"/>
</head>
<body>
<%//exception.printStackTrace(System.out);%>
<div class="bg">
</div>
<div class="errortext">
    <h1>Ops! Sembra che qualcosa sia andato storto. Riprova più tardi.</h1>
    <!--div style="color: #EE4037;">
            <Error message: <%//exception.getMessage();%>
        </div--><br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
</div>
</body>
</html>