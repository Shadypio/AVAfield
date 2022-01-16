<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/success.css"/>" rel="stylesheet">
    <title>Registrazione Effettuata</title>
    <link rel="icon" href="../images/logo_no_text.png" type="image/gif"/>
</head>

<style>
    h1, h3, a {
        color: #d1e5ff;
    }

</style>
<body>
<div class="bg">
</div>
<div class="registeredtext">
    <h1>Ti diamo il benvenuto!</h1>
    <h3>Il sito si pone come obiettivo quello di diventare un punto di riferimento tra le piattaforme di
        creazione di eventi sportivi. Prendendo spunto dalle features più interessanti dei siti competitors, AVAfield
        propone un’interfaccia semplice, minimale e funzionale sia dal punto di vista
        dell’utente, sia dal punto di vista amministrativo. All’interno del catalogo saranno presenti
        strutture sportive rivolte a chiunque cerchi svago nello sport.
        Allo scopo di coinvolgere sia utenti che utilizzano smartphone sia utenti che utilizzano
        computer, il sito web è strutturato in modo responsive.</h3>
    <br>
    <h3>Puoi trovarci su <a target="_blank" href="https://www.instagram.com" methods="_blank">Instagram</a>
        e su <a target="_blank" href="https://www.facebook.com">Facebook</a></h3>
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
</div>
</body>
</html>