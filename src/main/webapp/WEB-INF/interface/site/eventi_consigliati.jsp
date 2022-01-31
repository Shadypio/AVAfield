<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.evento.Evento" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Eventi consigliati</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- bootstrap css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/button.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="../images/logo_no_text.png" type="image/gif"/>
    <!-- Tweaks for older IEs-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <!-- fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- font awesome -->
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- owl stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesoeet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
          media="screen">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<a class="btn btn-primary btn-ghost btn-slide" href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
<section>
    <section class="section-products">
        <div class="container">
            <div class="row justify-content-center text-center">
                <div class="col-md-8 col-lg-6">
                    <div class="header">
                        <h3>Eventi consigliati</h3>
                        <h2>in base al tuo livello atletico</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <!-- Single Product -->
                    <%  int i=0;
                        ArrayList<Evento> eventi = (ArrayList<Evento>) request.getSession().getAttribute("listaEventi");%>
                <c:forEach items="${listaEventi}" var="evento">
                        <%Evento evento = eventi.get(i++);%>
                <div class="col-md-6 col-lg-4 col-xl-3"
                     onclick="window.open('<%=request.getContextPath()%>/ge/partecipaAdEvento?idEvento=${evento.idEvento}', '_self');">
                    <div id="product-1" class="single-product">
                        <div class="part-1">

                        </div>
                        <div class="part-2">

                            <h4 class="product-title"><%=evento.getNome() %>
                            </h4>
                            <p class="product-price"><span>Partecipanti: ${evento.numeroPartecipanti}</span></p>
                            <p class="product-price"><span>Data: ${evento.dataEvento}</span></p>
                        </div>
                    </div>
                </div>
                </c:forEach>
    </section>
</section>
</body>
</html>
