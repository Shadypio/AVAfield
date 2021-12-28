<%-- PAGINA DI LOGIN PER L'ADMIN (ERRORE SE è UN UTENTE NORMALE) --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form  action="${pageContext.request.contextPath}/ac/dashboard" method="post">
    <div class="imgcontainer">
        <img src="<%=request.getContextPath()%>/images/avalogo.png" class="avatar"/>
    </div>
    <fieldset>
        <h3>Login Admin</h3>
        <span> Email </span>
        <label for="email" class="field">
            <input type="email" name="email" id="email" placeholder="Email" required>
        </label>
        <span> Password </span>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password" required>
        </label>
        <button class="btn primary" type="submit">Accedi</button>
    </fieldset>
</form>
</body>
</html>
