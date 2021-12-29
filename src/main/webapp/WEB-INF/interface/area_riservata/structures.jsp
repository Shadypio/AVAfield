<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.struttura.Struttura" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- PAGINA PER ADMIN CONTROLLO STRUTTURE --%>
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
            <a href="<%=request.getContextPath()%>/gs/viewStructure">Gestione Strutture</a>
            <a href="<%=request.getContextPath()%>/gu/viewUser">Gestione Utenti</a>
            <a href="<%=request.getContextPath()%>/ge/viewEvent">Gestione Eventi</a>
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
                    <th>ID Struttura</th>
                    <th>Nome</th>
                    <th>Indirizzo</th>
                    <th>telefono</th>
                    <th>Descrizione</th>
                    <th>Categoria</th>
                    <th>Capienza</th>
                    <th>Numero Spogliatoi</th>
                    <th>Parcheggio</th>
                </tr>
                </thead>
                <tbody>
                <%int i=0;
                    ArrayList<Struttura> lista = (ArrayList<Struttura>) request.getSession().getAttribute("listaStrutture");%>
                <c:forEach var="s" items="${listaStrutture}">
                    <%Struttura s = lista.get(i++);%>
                    <tr>
                        <td>${s.idStruttura}</td>
                        <td>${s.nome}</td>
                        <td>${s.indirizzo}</td>
                        <td>${s.telefono}</td>
                        <td>${s.descrizione}</td>
                        <td>${s.categoria}</td>
                        <td>${s.capienza}</td>
                        <td>${s.numeroSpogliatoi}</td>
                        <td>${s.parcheggio}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="formWrapper">
                <form action="${pageContext.request.contextPath}/gs/deleteStruttura" method="post">
                    <select name="selezioneDelete" id="selectedDel">
                        <c:forEach var="s" items="${listaStrutture}">
                            <option>${s.idStruttura} </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="butDel btn primary">Elimina Struttura</button> <!--Button Delete-->
                </form>

                <form action="${pageContext.request.contextPath}/gs/updateStruttura" method="post" name="up" >
                    <select name="selezioneMod" id="selezioneMod">
                        <c:forEach var="s" items="${listaStrutture}">
                            <option>${s.idStruttura} </option>
                        </c:forEach>
                    </select>
                    <button class="butMod btn primary" type="button">Modifica Struttura</button> <!--Button Mod-->
                    <div class="modStr" name="upp">
                        <!--Al click Form Modify-->
                    </div>
                </form>

                <form action="<%=request.getContextPath()%>/gs/addStruttura" method="post">
                    <button class="butAdd btn primary" type="button">Aggiungi Struttura</button> <!--Button Add-->
                    <div class="newStr">
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
            $(".newStr").show().html("<fieldset>  <legend>Aggiungi Struttura</legend> <span> Nome: </span> <input type='text' name='nome' id='nome' placeholder='Nome' required> <br> " +
                "<span> Indirizzo: </span> <input type='text' name='indirizzo' id='indirizzo' placeholder='Indirizzo' required> <br>" +
                "<span> Telefono: </span> <input type='text' name='tel' id='tel' placeholder='Telefono' required> <br>" +
                "<span> Descrizione: </span> <input type='text' name='desc' id='desc' placeholder='Descrizione' required> <br>" +
                "<span> Capienza: <input type='number' name='capienza' id='capienza' placeholder='Capienza' required> <br>" +
                "<span> Categoria: </span> <input type='text' name='cat' id='cat' placeholder='Categoria' required> <br>" +
                "<span> Numero Spogliatoi: <input type='number' name='numSpo' id='numSpo' placeholder='Numero Spogliatoi' required> <br>" +
                "<span> Parcheggio: <input type='checkbox' name='park' id='park' placeholder='Parcheggio' required> <br>" +
                "<button class='btn primary' type='submit'>Salva</button> " +
                "<button class='btn primary' type='button' id='annulla'>Annulla</button> </fieldset>");
            $("#annulla").click(function () {
                $(".newStr").hide();
            });
        });
        $(".butMod").click(function () {
            $(".modStr").show().html("<fieldset>  <legend>Aggiungi Struttura</legend> <span> Nome: </span> <input type='text' name='nome' id='nome' placeholder='Nome' required> <br> " +
                "<span> Indirizzo: </span> <input type='text' name='indirizzo' id='indirizzo' placeholder='Indirizzo' required> <br>" +
                "<span> Telefono: </span> <input type='text' name='tel' id='tel' placeholder='Telefono' required> <br>" +
                "<span> Descrizione: </span> <input type='text' name='desc' id='desc' placeholder='Descrizione' required> <br>" +
                "<span> Capienza: <input type='number' name='capienza' id='capienza' placeholder='Capienza' required> <br>" +
                "<span> Categoria: </span> <input type='text' name='cat' id='cat' placeholder='Categoria' required> <br>" +
                "<span> Numero Spogliatoi: <input type='number' name='numSpo' id='numSpo' placeholder='Numero Spogliatoi' required> <br>" +
                "<span> Parcheggio: <input type='checkbox' name='park' id='park' placeholder='Parcheggio' required> <br>" +
                "<button class='btn primary' type='submit'>Salva</button> " +
                "<button class='btn primary' type='button' id='annulla2'>Annulla</button> </fieldset>")
            $("#annulla2").click(function () {
                $(".modStr").hide();
            });
        });
    });
</script>

</body>
</html>
