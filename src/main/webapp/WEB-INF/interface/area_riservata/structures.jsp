<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.struttura.Struttura" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- PAGINA PER ADMIN CONTROLLO STRUTTURE --%>
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
                        <th scope="col">ID Struttura</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Indirizzo</th>
                        <th scope="col">Telefono</th>
                        <th scope="col">Descrizione</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Capienza</th>
                        <th scope="col">Numero Spogliatoi</th>
                        <th scope="col">Parcheggio</th>
                        <th scope="col"></th>
                    </tr>
                    <tbody>
                        <%
                        int i = 0;
                        ArrayList<Struttura> lista = (ArrayList<Struttura>) request.getSession().getAttribute("listaStrutture");
                    %>
                    <c:forEach var="s" items="${listaStrutture}">
                            <%Struttura s = lista.get(i++);%>
                    <form action="${pageContext.request.contextPath}/gs/deleteStruttura" method="post">
                        <tr>
                            <input type="hidden" name="selezioneDelete" value="${s.idStruttura}">
                            <th scope="row">${s.idStruttura}</th>
                            <td>${s.nome}</td>
                            <td>${s.indirizzo}</td>
                            <td>${s.telefono}</td>
                            <td>${s.descrizione}</td>
                            <td>${s.categoria}</td>
                            <td>${s.capienza}</td>
                            <td>${s.numeroSpogliatoi}</td>
                            <td>${s.parcheggio}</td>
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

            var newForm = $("<form>").attr('action', '<%=request.getContextPath()%>/gs/addStruttura').attr('method', 'post').attr('class', 'tr');
            var cols = '';

            // Table columns
            cols += '<span class="td" scope="row"><%=lista.size()+1%></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="text" name="nome" id="nome "placeholder="Nome"></span>'
            cols += '<span class="td"><input class="form-control rounded-0" type="text" name="indirizzo" id="indirizzo" placeholder="Indirizzo" required"></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="text" name="tel" id="tel" placeholder="Telefono" required></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="text" name="desc" id="desc" placeholder="Descrizione" required"></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="number" name="capienza" id="capienza" placeholder="Capienza" required></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="text" name="cat" id="cat" placeholder="Categoria" required></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="number" name="numSpo" id="numSpo" placeholder="Numero Spogliatoi" required></span>';
            cols += '<span class="td"><input class="form-control rounded-0" type="checkbox" name="park" id="park" placeholder="Parcheggio" required></span>';
            cols += '<span class="td"><button class="btn btn-dark rounded-0" type="submit" id ="addRow"><i class="fa fa-trash"></i></button></span>';

            // Insert the columns inside a row
            newForm.append(cols);

            // Insert the row inside a table
            $("table").append(newForm);

        });

        // Remove row when delete btn is clicked
        $("table").on("click", "#deleteRow", function (event) {
            $(this).closest("form").remove();
            $(this).closest("tr").remove();
        });
    });
</script>

</body>
</html>
