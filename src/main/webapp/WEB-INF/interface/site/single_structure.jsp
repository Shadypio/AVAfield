<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.struttura.Struttura" %>
<%@ page import="model.recensione.Recensione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%Struttura struttura = (Struttura) request.getSession().getAttribute("struttura");%>
<head>
    <title><%=struttura.getNome()%>
    </title>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>Struttura</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- bootstrap css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/single_structure.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/button.css">
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
    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/35168705dc.js"></script>
</head>
<body>

<a class="btn btn-primary btn-ghost btn-slide" href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>

<div class="container mt-5 mb-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-10">
            <form class="card" action="${pageContext.request.contextPath}/ge/nuovoEvento" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="images p-3">
                            <div class="text-center p-4"><img id="main-image" src="../images/campo_img.png" width="250">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="product p-4">
                            <input type="hidden" name="idStruttura" value=<%=struttura.getIdStruttura()%>>
                            <div class="mt-4 mb-3"><span
                                    class="text-uppercase text-muted brand"><%=struttura.getCategoria()%></span>
                                <h5 class="text-uppercase"><%=struttura.getNome()%>
                                </h5>
                                <div class="price d-flex flex-row align-items-center"><span
                                        class="act-price"><%=struttura.getIndirizzo()%></span></div>
                            </div>
                            <p class="about"><h4>Organizza i tuoi eventi di: </h4><h5><%=struttura.getCategoria()%>
                        </h5><br>
                            <h4>Ospiti: </h4> <h5><%=struttura.getCapienza()%>
                        </h5><br>
                            <h4>Numero Spogliatoi: </h4> <h5><%=struttura.getNumeroSpogliatoi()%>
                        </h5><br>
                            <h4>Parcheggio: </h4> <%if (struttura.isParcheggio()) {%>
                            <h5>Sì</h5>
                            <%} else {%>
                            <h5>No</h5>
                            <%}%>
                        </h5><br>
                            <h4>Contattaci: </h4> <h5><%=struttura.getTelefono()%>
                        </h5><br></p>
                            <div class="cart mt-4 align-items-center">
                                <button class="btn btn-danger text-uppercase mr-2 px-4">Crea un evento</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container mt-5 mb-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-10">
            <div class="card" style="padding: 20px">
                <div class="review-heading">Recensioni</div>
                <p class="mb-20">
                <table>
                    <thead>
                    <tr>
                        <th>Titolo</th>
                        <th>Stelle</th>
                        <th>Testo</th>
                        <th>Username Utente</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int i = 0;
                        ArrayList<Recensione> recensioni = (ArrayList<Recensione>) request.getSession().getAttribute("listaRecensioni");
                    %>
                    <c:forEach var="recensione" items="${listaRecensioni}">
                        <%Recensione recensione = recensioni.get(i++);%>
                        <tr>
                            <td>${recensione.titolo}</td>
                            <td>${recensione.numeroStelle}</td>
                            <td>${recensione.testo}</td>
                            <td>${(recensione.utente).username}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </p>
                <form class="review-form" action="<%=request.getContextPath()%>/gr/addRecensione" method="post">
                    <div class="form-group">
                        <label>La tua valutazione</label>
                        <div class="reviews-counter">
                            <div class="rate">
                                <input type="radio" id="stelle5" name="stelle" value="5"/>
                                <label for="stelle5" title="text">5 stars</label>
                                <input type="radio" id="stelle4" name="stelle" value="4"/>
                                <label for="stelle4" title="text">4 stars</label>
                                <input type="radio" id="stelle3" name="stelle" value="3"/>
                                <label for="stelle3" title="text">3 stars</label>
                                <input type="radio" id="stelle2" name="stelle" value="2"/>
                                <label for="stelle2" title="text">2 stars</label>
                                <input type="radio" id="stelle1" name="stelle" value="1"/>
                                <label for="stelle1" title="text">1 star</label>

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Commento</label>
                        <textarea class="form-control" rows="10" name="testo" required></textarea>
                    </div>
                    <div class="row">
                        <input type="hidden" name="idStruttura" value="<%=struttura.getIdStruttura()%>">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Titolo</label>
                                <input type="text" name="titolo" class="form-control" placeholder="Titolo" required>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="round-black-btn">Salva</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugin.js"></script>
</body>
</html>
