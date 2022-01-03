<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="model.evento.Evento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- PAGINA PER ADMIN CONTROLLO EVENTI --%>

<html>
<head>
    <style>
        div.table {
            display: table;
        }

        form.tr, div.tr {
            display: table-row;
        }

        span.td {
            display: table-cell;
        }
    </style>
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
                        <th scope="col">ID Evento</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Numero Partecipanti</th>
                        <th scope="col">Data</th>
                        <th scope="col">Orario</th>
                        <th scope="col">ID Struttura</th>
                        <th scope="col"></th>
                    </tr>
                    <%
                        int i = 0;
                        ArrayList<Evento> eventi = (ArrayList<Evento>) request.getSession().getAttribute("listaEventi");
                    %>
                    <c:forEach var="evento" items="${listaEventi}">
                        <%Evento evento = eventi.get(i++);%>
                        <form action="${pageContext.request.contextPath}/ge/deleteEvento" method="post">
                            <tr>
                                <input type="hidden" name="selezioneDelete" value="${evento.idEvento}">
                                <th scope="row">${evento.idEvento}</th>
                                <td>${evento.nome}</td>
                                <td>${evento.numeroPartecipanti}</td>
                                <td>${evento.dataEvento}</td>
                                <td>${evento.orario}</td>
                                <td>${(evento.struttura).idStruttura}</td>
                                <td>
                                    <button type="submit" class="btn btn-danger rounded-0"><i class="fa fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>
            </div>
            <a class="btn btn-primary rounded-0 btn-block" id="insertRow" href="#">Add new row</a>
        </div>
    </div>
</div>

    <script>
        $(function () {


            $("#insertRow").on("click", function (event) {
                event.preventDefault();

                var newForm = $("<form>").attr('action', '<%=request.getContextPath()%>/ge/addEvento').attr('method', 'post').attr('class', 'tr');
                var cols = '';

                // Table columns
                cols += '<span class="td" scope="row"><%=eventi.size()+1%></span>';
                cols += '<span class="td"><input class="form-control rounded-0" type="text" name="nome" placeholder="Nome"></span>';
                cols += '<span class="td"><input class="form-control rounded-0" type="text" name="numeroPartecipanti" placeholder="Numero Partecipanti"></span>';
                cols += '<span class="td"><input class="form-control rounded-0" type="date" name="dataEvento" id="data" placeholder="Data evento" value="2021-12-25" min="2021-12-25" max="2022-12-25" required"></span>';
                cols += '<span class="td"><input class="form-control rounded-0" type="time" name="time" id="time" placeholder="Orario" required></span>';
                cols += '<span class="td"><input class="form-control rounded-0" type="number" name="idStruttura" id="idStruttura" placeholder="ID Struttura" required></span>';
                cols += '<span class="td"><button class="btn btn-dark rounded-0" type="submit" id ="addRow"><i class="fa fa-trash"></i></button></span>';

                // Insert the columns inside a row
                newForm.append(cols);

                // Insert the row inside a table
                $("table").append(newForm);

                // Increase counter after each row insertion
                counter++;
            });

            // Remove row when delete btn is clicked
            $("table").on("click", "#deleteRow", function (event) {
                $(this).closest("form").remove();
                $(this).closest("tr").remove();
                counter -= 1
            });
        });
    </script>
    </body>
</html>
