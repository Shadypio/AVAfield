<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- PAGINA PER ADMIN CONTROLLO UTENTI --%>
<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<main>
    <aside class="sidebar" id="sideBar">
        <nav>
            <img src="<%=request.getContextPath()%>/images/avalogo.png" width="100" height="115">
            <a href="<%=request.getContextPath()%>/ac/dashboard">Dashboard</a>
            <a href="<%=request.getContextPath()%>/gu/profileAdmin">Profilo</a>
            <a href="<%=request.getContextPath()%>/gs/viewStructures">Gestione Strutture</a>
            <a href="<%=request.getContextPath()%>/gu/viewUsers">Gestione Utenti</a>
            <a href="<%=request.getContextPath()%>/ge/viewEvents">Gestione Eventi</a>
            <a href="<%=request.getContextPath()%>/ac/logout">Logout</a>
        </nav>
    </aside>
    <section class="content grid-y" id="main">
        <div>
            <div>
                <button class="openbtn" onclick="toggleNav()"><img src="<%=request.getContextPath()%>/images/toggle-icon.png">
                </button>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>ID Utente</th>
                        <th>Nome</th>
                        <th>Cognome</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Autovalutazione</th>
                    </tr>
                </thead>
                <tbody>
                    <% int i=0; ArrayList<Utente> lista = (ArrayList<Utente>) request.getSession().getAttribute("listaUtenti");%>
                    <c:forEach var="u" items="${listaUtenti}">
                        <%Utente u = lista.get(i++);%>
                        <tr>
                            <td>${u.idUtente}</td>
                            <td>${u.nome}</td>
                            <td>${u.cognome}</td>
                            <td>${u.email}</td>
                            <td>${u.username}</td>
                            <td>${u.autovalutazione}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <form action="${pageContext.request.contextPath}/gu/deleteUtente" method="post">
                    <select name="selezioneDelete" id="selectedDel">
                        <c:forEach var="u" items="${listaUtenti}">
                            <option>${u.idUtente}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="butDel btn primary">Elimina Utente</button> <!--Button Delete-->
                </form>

                <form action="<%=request.getContextPath()%>/gu/addUtente" method="post">
                    <button class="butAdd btn primary" type="button">Aggiungi Utente</button> <!--Button Add-->
                    <div class="newUte">
                        <!--Al click Form Add-->
                    </div>
                </form>
            </div>
        </div>
    </section>


    <script>
        let status = false;

        function toggleNav() {
            if (status) {
                document.getElementById("sideBar").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";
                status = false;
            } else {
                document.getElementById("sideBar").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
                status = true;
            }
        }

        $(document).ready(function () {
            $(".butAdd").click(function () {
                $(".newUte").show().html("<fieldset>  <legend>Aggiungi Utente</legend> <span> Nome: </span> <input type='text' name='nome' id='nome' placeholder='Nome' required> <br> " +
                    "<span> Cognome: </span> <input type='text' name='cognome' id='cognome' placeholder='Cognome' required> <br>" +
                    "<span> Email: </span> <input type='text' name='email' id='email' placeholder='Email' required> <br>" +
                    "<span> Username: </span> <input type='text' name='username' id='username' placeholder='Username' required> <br>" +
                    "<span> Password: <input type='password' name='password' id='password' placeholder='Password' required> <br>" +
                    "<span> Is Admin: </span> <input type='checkbox' name='admin' id='admin' placeholder='Admin' required> <br>" +
                    "<span> Telefono: <input type='number' name='telefono' id='telefono' placeholder='Telefono' required> <br>" +
                    "<span> Autovalutazione: <input type='range' min='0' max='5' name='auto' id='auto' placeholder='Autovalutazione' required> <br>" +
                    "<button class='btn primary' type='submit'>Salva</button> " +
                    "<button class='btn primary' type='button' id='annulla'>Annulla</button> </fieldset>");
                $("#annulla").click(function () {
                    $(".newUte").hide();
                });
            });
        });
    </script>
</main>
</body>
</html>
