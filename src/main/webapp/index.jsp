<%@ page import="model.struttura.Struttura" %>
<%@ page import="model.struttura.StrutturaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="it">
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
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="images/logo_no_text.png" type="image/gif"/>
    <!-- Scrollbar Custom CSS -->
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <!-- Tweaks for older IEs-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <!-- fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- font awesome -->
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  -->
    <!-- owl stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesoeet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
          media="screen">
</head>
<body>
<%
    StrutturaDAO strDAO = new StrutturaDAO();
    ArrayList<Struttura> strutture = strDAO.doRetrieveAll();

    int i = 0;%>
<!-- banner bg main start -->
<div class="banner_bg_main">
    <!-- header top section start -->
    <div class="container">
        <div class="header_section_top">
            <div class="row">
                <div class="col-sm-12">
                    <div class="custom_menu">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/ac/secret">Area Riservata</a></li>
                            <li><a href="#">Contatti</a></li>
                            <li><a href="#">Segnalaci un problema</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- header top section start -->
    <!-- logo section start -->
    <div class="logo_section">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="logo"><a href="../../../../../Downloads/eflyer-master/index.html"><img
                            src="images/avalogo.png"></a></div>
                </div>
            </div>
        </div>
    </div>
    <!-- logo section end -->
    <!-- header section start -->
    <div class="header_section">
        <div class="container">
            <div class="containt_main">
                <div id="mySidenav" class="sidenav">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                    <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
                    <a href="<%=request.getContextPath()%>/ac/signin_signup">Login</a>
                </div>
                <span class="toggle_icon" onclick="openNav()"><img src="images/toggle-icon.png"></span>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categorie
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">NON LO SO</a>
                        <a class="dropdown-item" href="#">DOBBIAMO</a>
                        <a class="dropdown-item" href="#">PENSARCI</a>
                        <a class="dropdown-item" href="#">NON LO VOGLIO TOGLIERE</a>
                        <a class="dropdown-item" href="#">MI PIACE</a>
                    </div>
                </div>
                <div class="main">
                    <!-- Another variation with a button -->
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Cerca una struttura">
                        <div class="input-group-append">
                            <button class="btn btn-secondary" type="button"
                                    style="background-color: #f26522; border-color:#f26522 ">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="header_box">
                    <div class="login_menu">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/ac/signin_signup">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span class="padding_10">Sign in / Sign up</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- header section end -->
    <!-- banner section start -->
    <div class="banner_section layout_padding">
        <div class="container">
            <div id="my_slider" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col-sm-12">
                                <h1 class="banner_taital">Crea<br> un Evento</h1>
                                <div class="buynow_bt"><a href="#">Crea</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row">
                            <div class="col-sm-12">
                                <h1 class="banner_taital">Partecipa<br> ad un evento</h1>
                                <div class="buynow_bt"><a href="#">Esplora</a></div>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#my_slider" role="button" data-slide="prev">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a class="carousel-control-next" href="#my_slider" role="button" data-slide="next">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- banner section end -->
</div>
<!-- banner bg main end -->
<!-- electronic section start -->
<div class="fashion_section" style="padding-top: 50px">
    <h1 class="fashion_taital">Alcune Strutture disponibili</h1>
    <div id="electronic_main_slider" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="container">
                    <div class="fashion_section_2">
                        <div class="row">
                            <%for (; i < 3; i++) { %>
                            <div class="col-lg-4 col-sm-4">
                                <div class="box_main">

                                    <div class="box_main">
                                        <h4 class="shirt_text"><%=strutture.get(i).getNome() %>
                                        </h4>
                                        <p class="price_text"><span
                                                style="color: #262626;"><%=strutture.get(i).getIndirizzo()%></span>
                                        </p>
                                        <div class="electronic_img"><img src="images/campo_img.png"></div>
                                        <div class="btn_main">
                                            <div class="buy_bt"><a href="#">Prenota</a></div>
                                            <div onclick="window.open('<%=request.getContextPath()%>/cliente/singleStructure?id=<%=strutture.get(i).getIdStruttura()%>', '_self');">
                                                <div class="seemore_bt"><a href="#">Dettagli</a></div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container">
                    <div class="fashion_section_2">
                        <div class="row">
                            <%for (; i < 6; i++) { %>
                            <div class="col-lg-4 col-sm-4">
                                <div class="box_main">

                                    <div class="box_main">
                                        <h4 class="shirt_text"><%=strutture.get(i).getNome() %>
                                        </h4>
                                        <p class="price_text"><span
                                                style="color: #262626;"><%=strutture.get(i).getIndirizzo()%></span>
                                        </p>
                                        <div class="electronic_img"><img src="images/campo_img.png"></div>
                                        <div class="btn_main">
                                            <div class="buy_bt"><a href="#">Prenota</a></div>
                                            <div onclick="window.open('<%=request.getContextPath()%>/cliente/singleStructure?id=<%=strutture.get(i).getIdStruttura()%>', '_self');">
                                                <div class="seemore_bt"><a href="#">Dettagli</a></div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container">
                    <div class="fashion_section_2">
                        <div class="row">
                            <%for (; i < 9; i++) { %>
                            <div class="col-lg-4 col-sm-4">
                                <div class="box_main">
                                    <div class="box_main">
                                        <h4 class="shirt_text"><%=strutture.get(i).getNome() %>
                                        </h4>
                                        <p class="price_text"><span
                                                style="color: #262626;"><%=strutture.get(i).getIndirizzo()%></span>
                                        </p>
                                        <div class="electronic_img"><img src="images/campo_img.png"></div>
                                        <div class="btn_main">
                                            <div class="buy_bt"><a href="#">Prenota</a></div>
                                            <div onclick="window.open('<%=request.getContextPath()%>/cliente/singleStructure?id=<%=strutture.get(i).getIdStruttura()%>', '_self');">
                                                <div class="seemore_bt"><a href="#">Dettagli</a></div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#electronic_main_slider" role="button" data-slide="prev">
            <i class="fa fa-angle-left"></i>
        </a>
        <a class="carousel-control-next" href="#electronic_main_slider" role="button" data-slide="next">
            <i class="fa fa-angle-right"></i>
        </a>
    </div>
</div>
<!-- electronic section end -->
<!-- footer section start -->
<div class="footer_section layout_padding">
    <div class="container">
        <div class="footer_logo"><a href=""><img src="images/avalogo.png"></a></div>
        <div class="location_main">I nostri social: <a href="#">Facebook </a><a href="#">Instagram</a></div>
    </div>
</div>
<!-- footer section end -->
<!-- copyright section start -->
<div class="copyright_section">
    <div class="container">
        <p class="copyright_text">© 2021 All Rights Reserved. Design by <a>AVATeam</a></p>
    </div>
</div>
<!-- copyright section end -->
<!-- Javascript files-->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/plugin.js"></script>
<!-- sidebar -->
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/custom.js"></script>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
</script>
</body>
</html>