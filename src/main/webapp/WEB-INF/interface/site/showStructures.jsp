<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.struttura.Struttura" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- PAGINA CON LISTA DI STRUTTURE GIA PRESENTI VISUALIZZABILE DA TUTTI
     OGNI STRUTTURA è RECENSIONABILE DA REGISTRATI SOLAMENTE--%>

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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/button.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="images/logo_no_text.png" type="image/gif"/>
    <!-- fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- owl stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesoeet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
          media="screen">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/35168705dc.js"></script>
</head>
<body>
<button  class="btn btn-primary btn-ghost btn-slide"><a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a></button>
<section class="section-products">
    <div class="container">
        <div class="row justify-content-center text-center">
            <div class="col-md-8 col-lg-6">
                <div class="header">
                    <h3>Le nostre Strutture</h3>
                    <h2>Affiliate</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <!-- Single Product -->
            <% int i = 0;
                ArrayList<Struttura> strutture = (ArrayList<Struttura>) request.getSession().getAttribute("listaStrutture");%>
            <c:forEach items="${listaStrutture}" var="evento">
                <%Struttura struttura = strutture.get(i++);%>
                <div class="col-md-6 col-lg-4 col-xl-3"
                     onclick="window.open('<%=request.getContextPath()%>/gs/singleStructure?idStruttura=<%=struttura.getIdStruttura()%>', '_self');">
                    <div id="product-1" class="single-product">
                        <div class="part-1">

                        </div>
                        <div class="part-2">
                            <h4 class="product-title"><%=struttura.getNome() %>
                            </h4>
                            <p class="product-price"><span><%=struttura.getIndirizzo()%></span></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
</section>


<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugin.js"></script>
</body>
</html>
