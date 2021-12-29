<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.evento.Evento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- PAGINA PER ADMIN CONTROLLO EVENTI --%>
<html>
<head>
    <title></title>
</head>
<body>
<main>
    <aside class="sidebar" id="sideBar">
        <nav>
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
            <div>
                <button class="openbtn" onclick="toggleNav()"><img src="<%=request.getContextPath()%>/images/toggle-icon.png">
                </button>
            </div>
            <table>
                <thead>
                <tr>
                    <th>ID Evento</th>
                    <th>Nome</th>
                    <th>Numero Partecipanti</th>
                    <th>Data</th>
                    <th>Orario</th>
                    <th>ID Struttura</th>
                </tr>
                </thead>
                <tbody>
                <%int i=0;
                ArrayList<Evento> eventi = (ArrayList<Evento>) request.getSession().getAttribute("listaEventi");%>
                <c:forEach var="evento" items="${listaEventi}">
                    <%Evento evento = eventi.get(i++);%>
                    <tr>
                        <td>${evento.idEvento}</td>
                        <td>${evento.nome}</td>
                        <td>${evento.numeroPartecipanti}</td>
                        <td>${evento.dataEvento}</td>
                        <td>${evento.orario}</td>
                        <td>${(evento.struttura).idStruttura}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="formWrapper">
                <form action="${pageContext.request.contextPath}/ge/deleteEvento" method="post">
                    <select name="selezioneDelete" id="selectedDel">
                        <c:forEach var="eve" items="${listaEventi}">
                            <option>${eve.idEvento} </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="butDel btn primary">Elimina Evento</button> <!--Button Delete-->
                </form>

                <form action="${pageContext.request.contextPath}/ge/updateEvento" method="post" name="up" >
                    <select name="selezioneMod" id="selezioneMod">
                        <c:forEach var="eve" items="${listaEventi}">
                            <option>${eve.idEvento} </option>
                        </c:forEach>
                    </select>
                    <button class="butMod btn primary" type="button">Modifica Evento</button> <!--Button Mod-->
                    <div class="modEve" name="up">
                        <!--Al click Form Modify-->
                    </div>
                </form>

                <form action="<%=request.getContextPath()%>/ge/addEvento" method="post">
                    <button class="butAdd btn primary" type="button">Aggiungi Evento</button> <!--Button Add-->
                    <div class="newEve">
                        <!--Al click Form Add-->
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>
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
            $(".newEve").show().html("<fieldset>  <legend>Aggiungi Evento</legend> <span> Nome: </span> <input type='text' name='nome' id='nome' placeholder='Nome' required> <br> " +
                "<span> Numero Partecipanti: <input type='number' name='numero' id='numero' placeholder='Numero Partecipanti' required> <br>" +
                "<span> Data: </span> <input type='date' name='data' id='data' value='2021-12-25' min='2021-12-25' max='2022-12-25' required><br>" +
                "<span> Orario: </span> <input type='time' name='time' id='time' required><br>" +
                "<span> ID Struttura: </span> <input type='number' name='idStr' id='idStr' placeholder='ID Struttura' required> <br>" +
                "<button class='btn primary' type='submit'>Salva</button> " +
                "<button class='btn primary' type='button' id='annulla'>Annulla</button> </fieldset>");
            $("#annulla").click(function () {
                $(".newPro").hide();
            });
        });
        $(".butMod").click(function () {
            $(".modPro").show().html("<fieldset>  <legend>Aggiungi Evento</legend> <span> Nome: </span> <input type='text' name='nome' id='nome' placeholder='Nome' required> <br> " +
                "<span> Numero Partecipanti: <input type='number' name='numero' id='numero' placeholder='Numero Partecipanti' required> <br>" +
                "<span> Data: </span> <input type='date' name='data' id='data' value='2021-12-25' min='2021-12-25' max='2022-12-25' required><br>" +
                "<span> Orario: </span> <input type='time' name='time' id='time' required><br>" +
                "<span> ID Struttura: </span> <input type='number' name='idStr' id='idStr' placeholder='ID Struttura' required> <br>" +
                "<button class='btn primary' type='submit'>Salva</button>" +
                "<button class='btn primary' type='button' id='annulla2'>Annulla</button> </fieldset>")
            $("#annulla2").click(function () {
                $(".modPro").hide();
            });
        });
    });
</script>
</body>
</html>
