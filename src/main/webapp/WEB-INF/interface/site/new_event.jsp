<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
    <link rel="icon" href="images/logo_no_text.png" type="image/gif"/>
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
<body style="background: white">
<% LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");%>
    <div class="align-content-center">
        <form class="p-3 py-5" action="${pageContext.request.contextPath}/ge/addEvento" method="post">
            <!-- onsubmit="return validatePass()" -->
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h4 style="text-align: center">CREA UN EVENTO</h4>
            </div>
            <div class="row mt-3">
                <input type="hidden" name="idStr" value="<%=Integer.parseInt((String) request.getAttribute("idStruttura"))%>">
                <div class="col-md-6"><label class="labels">Nome evento</label><input type="text" class="form-control"
                                                                                      name="nome" id="nome"
                                                                                      placeholder="Nome"></div>
                <div class="col-md-6"><label class="labels">Numero partecipanti</label><input type="text"
                                                                                              class="form-control"
                                                                                              name="numero"
                                                                                              id="numeroPartecipanti"
                                                                                              placeholder="Numero Partecipanti"
                                                                                              required></div>
            </div>
            <div class="row mt-2">
                <div class="col-md-12"><label class="labels">Data evento</label><input type="date" class="form-control"
                                                                                       name="data" id="data"
                                                                                       placeholder="Data"
                                                                                       value='<%=""+currentDate.format(formatter)%>'
                                                                                       min='<%=""+currentDate.format(formatter)%>'
                                                                                       max='2022-12-25' required></div>
                <div class="col-md-12"><label class="labels">Orario</label> <input type="time" class="form-control"
                                                                                   name="time" id="time"
                                                                                   placeholder="Orario" required></div>
            </div>
            <div class="mt-5 text-center">
                <button class="btn btn-primary profile-button" type="submit" name="registrati">Salva</button>
                <button class="btn btn-primary profile-button" type="button" id="annulla">Annulla</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
