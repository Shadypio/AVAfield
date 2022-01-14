<%-- PAGINA DI LOGIN PER L'ADMIN (ERRORE SE è UN UTENTE NORMALE) --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
    <title>Area riservata</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- bootstrap css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="../images/logo_no_text.png" type="image/gif" />
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
<style>
    .grid-y {
        display: flex;
        flex-flow: column wrap;
    }

    .cell {
        flex-basis: 100%;
    }

    .w50 {
        flex-basis: 50%;
    }

    .login{
        padding: 1rem;
        background-color: lightblue;
    }

</style>
<c:if test="${failedAdmin}">
    <div class="alert grid-y cell w50 login">
        <p>Non sei autorizzato</p>
        <% request.getSession().setAttribute("failedAdmin",false);%>
        <button type="button" class="okAlert">OK</button>
    </div>
</c:if>
<div class="d-flex justify-content-center align-items-center mt-5">
    <div class="card">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item text-center"> <a class="nav-link active btl" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Login Admin</a> </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                <div class="form px-4 pt-5">
                    <form action="${pageContext.request.contextPath}/ac/dashboard" method="post">
                        <input class="form-control" type="email" name="email" id="email" placeholder="Email" required>
                        <input class="form-control" type="password" name="password" id="password" placeholder="Password" required>
                        <button class="btn btn-dark btn-block" name="login" >Login</button>
                        <a class="btn btn-block btn-dark" href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(".okAlert").click(function () {
        $(".alert").hide();
    });
</script>
</body>
</html>
