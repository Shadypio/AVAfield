<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- PAGINA PER ADMIN CONTROLLO UTENTI --%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sb-admin-2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/single_page_dashboard.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div id="page-top">
    <div id="wrapper">
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon">
                    <img src="<%=request.getContextPath()%>/images/avalogo.png" width="55" height="55">
                </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="<%=request.getContextPath()%>/ac/dashboard">
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/gu/profileAdmin">
                    <span>Profilo</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/gs/viewStructures">
                    <span>Gestione Strutture</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/gu/viewUsers">
                    <span>Gestione Utenti</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ge/viewEvents">
                    <span>Gestione Eventi</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ac/logout">
                    <span>Logout</span></a>
            </li>
        </ul>

        <div class="container-fluid" style="padding-top: 30px">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th scope="col">ID Utente</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Cognome</th>
                        <th scope="col">Email</th>
                        <th scope="col">Username</th>
                        <th scope="col">Autovalutazione</th>
                    </tr>
                    <%
                        int i = 0;
                        ArrayList<Utente> lista = (ArrayList<Utente>) request.getSession().getAttribute("listaUtenti");
                    %>
                    <c:forEach var="u" items="${listaUtenti}">
                        <%Utente u = lista.get(i++);%>
                        <form action="${pageContext.request.contextPath}/gu/deleteUtente" method="post">
                            <tr>
                                <input type="hidden" name="selezioneDelete" value="${u.idUtente}">
                                <th scope="row">${u.idUtente}</th>
                                <td>${u.nome}</td>
                                <td>${u.cognome}</td>
                                <td>${u.email}</td>
                                <td>${u.username}</td>
                                <td>${u.autovalutazione}</td>
                                <td>
                                    <button type="submit" class="btn btn-danger rounded-0"><i class="fa fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>
            </div>
            <form action="<%=request.getContextPath()%>/gu/addUtente" method="post">
                <button class="butAdd btn primary" type="button">Aggiungi Utente</button> <!--Button Add-->
                <div class="newUte">
                    <!--Al click Form Add-->
                </div>
            </form>
        </div>
    </div>
    <div>

        <script>
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

                // Remove row when delete btn is clicked
                $("table").on("click", "#deleteRow", function (event) {
                    $(this).closest("form").remove();
                    $(this).closest("tr").remove();
                    counter -= 1
                });
            });

        </script>
        </main>
</body>
</html>
