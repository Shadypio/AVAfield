<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- PAGINA GESTIONE DEI DATI DELL'ACCOUNT DI UN UTENTE LOGGATO
     VISUALIZZARE ANCHE GLI EVENTI LEGATI ALL'UTENTE CHE è ENTRATO --%>

<html>
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>AVAField</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- bootstrap css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="images/logo_no_text.png" type="image/gif" />
    <!-- Tweaks for older IEs-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <!-- fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- font awesome -->
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- owl stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesoeet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<body>
<%Utente u = (Utente) session.getAttribute("profilo");%>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold"><%=u.getNome()%></span><span class="text-black-50"><%=u.getEmail()%></span><span><a class="btn btn-primary profile-button" href="<%=request.getContextPath()%>/index.jsp" style="margin-top: 20px">Torna alla Home</a></span><span><a class="btn btn-primary profile-button" href="<%=request.getContextPath()%>/ac/logout" style="margin-top: 10px">Logout</a></span><span> </span></div>
        </div>
        <div class="col-md-5 border-right">
            <form class="p-3 py-5" action="${pageContext.request.contextPath}/gu/updateUtente" method="post"> <!-- onsubmit="return validatePass()" -->
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profilo</h4>
                </div>
                <div class="row mt-2">
                    <input type="hidden" name="id" value="<%=u.getIdUtente()%>">
                    <div class="col-md-6"><label class="labels">Nome</label><input type="text" class="form-control" value="<%=u.getNome()%>" readonly name="nome"></div>
                    <div class="col-md-6"><label class="labels">Cognome</label><input type="text" class="form-control" value="<%=u.getCognome()%>" readonly name="cognome"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control" value="<%=u.getEmail()%>" readonly name="email"></div>
                    <div class="col-md-12"><label class="labels">Username</label> <input type="text" class="form-control" value="<%=u.getUsername()%>" readonly name="username"></div>
                    <div class="col-md-12"><label class="labels">Password</label><input type="password" class="form-control" value="<%=u.getPassword()%>" readonly name="password" id="password"></div>
                    <div class="col-md-12"><label class="labels">Telefono</label><input type="text" class="form-control" value="<%=u.getNumeroTelefono()%>" readonly name="telefono"></div>
                </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit" onclick="salva()">Salva</button> <button class="btn btn-primary profile-button" type="button" id="mod">Modifica</button></div>
            </form>
        </div>
        <div class="col-md-4">
            <div class="p-3 py-5 scrollmenu">
                <table>
                    <tr>
                        <th class="labels">ID Evento</th>
                        <th class="labels">Numero Partecipanti</th>
                        <th class="labels">Data</th>
                        <th class="labels">Nome Struttura</th>
                        <th class="labels">Indirizzo Struttura</th>
                        <th class="labels">Parcheggio</th>
                    </tr>
                    <c:forEach var="evento" items="${listaEventi}">
                        <tr>
                        <td class="labels">${evento.idEvento}</td>
                        <td class="labels">${evento.numeroPartecipanti}</td>
                        <td class="labels">${evento.dataEvento}</td>
                        <td class="labels">${(evento.struttura).nome}</td>
                        <td class="labels">${(evento.struttura).indirizzo}</td>
                        <td class="labels">${(evento.struttura).parcheggio}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
</div>

    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugin.js"></script>
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
