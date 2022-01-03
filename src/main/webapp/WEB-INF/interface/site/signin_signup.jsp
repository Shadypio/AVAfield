<%-- PAGINA DI REGISTRAZIONE E LOGIN DA PARTE DI UN UTENTE (ERRORE SE è UN ADMIN) --%>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign.css">
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
</head>
<body>

<div class="d-flex justify-content-center align-items-center mt-5">
    <div class="card">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item text-center"> <a class="nav-link active btl" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Login</a> </li>
            <li class="nav-item text-center"> <a class="nav-link btr" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Registrazione</a> </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                <div class="form px-4 pt-5">
                    <form action="${pageContext.request.contextPath}/ac/signin" method="post">
                        <input class="form-control" type="email" name="email" id="email" placeholder="Email" required>
                        <input class="form-control" type="password" name="password" id="password" placeholder="Password" required>
                        <button class="btn btn-dark btn-block" name="login" >Login</button>
                        <a class="btn btn-dark btn-block" href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
                    </form>
                </div>
            </div>
            <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                <div class="form px-4">
                    <form action="${pageContext.request.contextPath}/ac/create" method="post">
                        <input class="form-control" type="text" name="nome" id="nome" placeholder="Nome" required>
                        <input class="form-control" type="text" name="cognome" id="cognome" placeholder="Cognome" required>
                        <input class="form-control" type="text" name="username" id="username" placeholder="Username" required>
                        <input class="form-control" type="email" name="email" id="email" placeholder="Email">
                        <input class="form-control" onfocusout="hideInfoPassword()" onfocusin="showInfoPassword()" type="password" name="password" id="password" placeholder="Password" required>
                        <input class="form-control" type="password" name="confermapassword" id="confermapassword" placeholder="Conferma Password" required>
                        <input class="form-control" type="text" name="telefono" id="telefono" placeholder="Telefono" required>
                        <label for="autovalutazione">Autovalutazione (tra 0 e 5):</label>
                        <input class="form-control" type="range" min="0" max="5" name="autovalutazione" id="autovalutazione" required>
                        <button class="btn btn-dark btn-block" name="registrati">Registrati</button>
                        <a class="btn btn-dark btn-block" href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
                    </form>
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

    function showInfoPassword(){
        var x = document.getElementById("info")
        x.style.display = "block"

        var y = document.getElementById("inner");
        y.style.display = "block"
    }
    /**
     * REGEX FOR THIS PASSWORD
     * /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$/
     *

     ^.*              : Start
     (?=.{8,10})      : Length
     (?=.*[a-zA-Z])   : Letters
     (?=.*\d)         : Digits
     (?=.*[!#$%&? "]) : Special characters
     .*$              : End

     */

    function validateTelPass() {
        var str1 = document.getElementById("password").value;
        var str2 = document.getElementById("telefono").value;
        var patt1 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$/;
        var patt2 = /[0-9]/g;
        var result1 = str1.match(patt1)
        var result2 = str2.match(patt2);
        var x = result1;
        var y = result2;
        var p = document.getElementById("password").value;
        var q = document.getElementById("confermapassword").value;

        if (x== str1 && y.length == 10 && p===q){
            return true;
        }else {
            alert('Compila Correttamente!')
            return false
        }
    }

    function hideInfoPassword(){
        document.getElementById("inner").style.display = "none"
    }
</script>
</body>
</html>
