<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- PAGINA GESTIONE DATI DELL'ADMIN IN AREA RISERVATA UNA VOLTA LOGGATO --%>

<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<main>
    <aside class="sidebar" id="sideBar">
        <nav class="grid-y align-center">
            <img src="<%=request.getContextPath()%>/images/avalogo.png" width="100" height="115">
            <a href="<%=request.getContextPath()%>/ac/dashboard">Dashboard</a>
            <a href="<%=request.getContextPath()%>/gu/profileAdmin">Profilo</a>
            <a href="<%=request.getContextPath()%>/gs/viewStructure">Gestione Strutture</a>
            <a href="<%=request.getContextPath()%>/gu/viewUser">Gestione Utenti</a>
            <a href="<%=request.getContextPath()%>/ge/viewEvent">Gestione Eventi</a>
            <a href="<%=request.getContextPath()%>/gr/viewReview">Gestione Recensioni</a>
            <a href="<%=request.getContextPath()%>/ac/logout">Logout</a>

        </nav>
    </aside>
    <section class="content grid-y" id="main">
        <div>
            <button class="openbtn" onclick="toggleNav()"><img src="<%=request.getContextPath()%>/images/toggle-icon.png"></button>
        </div>
        <fieldset class="grid-y cell w50 login">
            <legend>Profilo Admin:</legend>
            <form action="${pageContext.request.contextPath}/gu/updateAdmin" method="post">
                <%Utente u= (Utente) session.getAttribute("profilo");%>
                <span>ID: </span>
                <input type="hidden" name="idAdmin" value="<%=u.getIdUtente()%>">
                <span>Nome: </span>
                <input type="text" value="<%=u.getNome()%>" readonly name="nome"><br>
                <span>Cognome: </span>
                <input type="text" value="<%=u.getCognome()%>" readonly name="cognome"><br>
                <span>Email: </span>
                <input type="text" value="<%=u.getEmail()%>" readonly name="email"><br>
                <span>Username: </span>
                <input type="text" value="<%=u.getUsername()%>" readonly name="username"><br>
                <span>Password: </span>
                <input type="password" value="<%=u.getPassword()%>" readonly name="password"><br>
                <span>Telefono: </span>
                <input type="text" value="<%=u.getNumeroTelefono()%>" readonly name="telefono"><br>
                <button type="submit" onclick="salva()" class="btn primary">Salva</button>
                <button type="button" id="mod" class="btn primary">Modifica</button>
            </form>
        </fieldset>
    </section>
</main>

<script>
    let status = false;

    function toggleNav(){
        if(status) {
            document.getElementById("sideBar").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
            status = false;
        }else{
            document.getElementById("sideBar").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
            status = true;
        }
    }

    $(document).ready(function(){
        $("#mod").click(function () {
            $("input").removeAttr("readonly");
            $("input").css("opacity", "100%");
            read = false;
        })
    });
    function salva(){
        var elemento = document.getElementsByTagName("input");
        var i;
        for(i=0; i<elemento.length; i++)
            elemento[i].setAttribute("readonly", true);
    }
</script>

</body>
</html>
