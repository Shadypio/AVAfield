<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- PAGINA GESTIONE DEI DATI DELL'ACCOUNT DI UN UTENTE LOGGATO
     VISUALIZZARE ANCHE GLI EVENTI LEGATI ALL'UTENTE CHE è ENTRATO --%>

<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<body>
<div class="bg">
</div>
<div>
    <h1>Bentornato!</h1>
    <h3>Login effettuato con successo.</h3>
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
    <a href="<%=request.getContextPath()%>/ac/logout">Logout</a>


    <section >
        <fieldset >
            <legend> Profilo:</legend>
            <form action="${pageContext.request.contextPath}/gu/updateUtente" method="post"  > <!-- onsubmit="return validatePass()" -->
                <%Utente u = (Utente) session.getAttribute("profilo");%>
                <input type="hidden" name="id" value="<%=u.getIdUtente()%>">
                <span>Nome: </span>
                <input type="text" value="<%=u.getNome()%>" readonly name="nome"><br>
                <span>Cognome: </span>
                <input type="text" value="<%=u.getCognome()%>" readonly name="cognome"><br>
                <span>Email: </span>
                <input type="text" value="<%=u.getEmail()%>" readonly name="email"><br>
                <span>Username: </span>
                <input type="text" value="<%=u.getUsername()%>" readonly name="username"><br>
                <span>Password: </span>
                <input type="password" value="<%=u.getPassword()%>" readonly name="password" id="password"><br>
                <span>Telefono: </span>
                <input type="text" value="<%=u.getNumeroTelefono()%>" readonly name="telefono"><br><br>
                <button type="submit" onclick="salva()" class="btn primary">Salva</button>
                <button type="button" id="mod" class="btn primary">Modifica</button>
            </form>
        </fieldset>
    </section>

    <section>
        <fieldset>
            <legend> Eventi:</legend>
            <table>
                <tr>
                    <th>ID Evento</th>
                    <th>Numero Partecipanti</th>
                    <th>Data</th>
                    <th>Nome Struttura</th>
                    <th>Indirizzo Struttura</th>
                    <th>Parcheggio</th>
                </tr>
                <c:forEach var="evento" items="${listaEventi}">
                    <tr>
                        <td>${evento.idEvento}</td>
                        <td>${evento.numeroPartecipanti}</td>
                        <td>${evento.dataEvento}</td>
                        <td>${(evento.struttura).nome}</td>
                        <td>${(evento.struttura).indirizzo}</td>
                        <td>${(evento.struttura).parcheggio}</td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
    </section>
    <!-- <a href="<%=request.getContextPath()%>/ge/showord?id=${order.idOrdine}">${order.idOrdine}</a>--!>

    <script>
        $(document).ready(function () {
            $("#mod").click(function () {
                $("input").removeAttr("readonly");
                $("input").css("opacity", "100%");
            })
        });

        function salva() {
            var elemento = document.getElementsByTagName("input");
            var i;
            for (i = 0; i < elemento.length; i++)
                elemento[i].setAttribute("readonly", true);
        }

        function validatePass() {
            var str1 = document.getElementById("password").value;
            var patt1 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$/;
            var result1 = str1.match(patt1)
            var x = result1;
            if (x== str1 ){
                return true;
            }else {
                alert('Compila Password Correttamente!')
                return false
            }
        }

    </script>


</body>
</html>
