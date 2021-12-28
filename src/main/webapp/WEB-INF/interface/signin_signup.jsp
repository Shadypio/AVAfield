<%-- PAGINA DI REGISTRAZIONE E LOGIN DA PARTE DI UN UTENTE (ERRORE SE è UN ADMIN) --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/ac/signin"  method="post">
    <div class="imgcontainer">
        <img src="<%=request.getContextPath()%>/images/avalogo.png" class="avatar"/>
    </div>
    <fieldset>
        <h3>Login Utente</h3>
        <span> Email </span>
        <label for="email" class="field">
            <input type="email" name="email" id="email" placeholder="Email" required>
        </label>
        <span> Password </span>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password" required>
        </label>
        <button class="btn primary" type="submit">Accedi</button>
    </fieldset>
</form>


<div class="content">
    <form  action="${pageContext.request.contextPath}/ac/create"  method="post" onsubmit="return validateTelPass()">
        <div class="imgcontainer">
            <img src="<%=request.getContextPath()%>/img/logo.png" class="avatar"/>
        </div>
        <fieldset class="grid-y cell w50 login">
            <h3>Registrazione Utente</h3>
            <span> Nome </span>
            <label for="nome" class="field">
                <input type="text" name="nome" id="nome" placeholder="Nome" required>
            </label>

            <span> Cognome </span>
            <label for="cognome" class="field">
                <input type="text" name="cognome" id="cognome" placeholder="Cognome" required>
            </label>

            <span> Username </span>
            <label for="username" class="field">
                <input type="text" name="username" id="username" placeholder="Username" required>
            </label>

            <span> Email </span>
            <label for="email" class="field">
                <input type="email" name="email" id="email" placeholder="Email">
            </label>

            <span> Password </span>
            <label for="password" class="field">
                <input onfocusout="hideInfoPassword()" onfocusin="showInfoPassword()" type="password" name="password" id="password" placeholder="Password" required><br>
            </label>

            <div class="inner" id="inner">
                <div id="info">La password deve essere lunga tra gli 8 e i 10 caratteri, deve contenere almeno una minuscola,
                    una maiuscola, un numero e un carattere speciale tra @$!%*?&</div>
            </div>

            <span>Conferma  Password </span>
            <label for="confermapassword" class="field">
                <input type="password" name="confermapassword" id="confermapassword" placeholder="Conferma Password" required><br>
            </label>

            <span> Indirizzo </span>
            <label for="indirizzo" class="field">
                <input type="text" name="indirizzo" id="indirizzo" placeholder="Indirizzo" required>
            </label>

            <span> Telefono </span>
            <label for="telefono" class="field">
                <input type="text" name="telefono" id="telefono" placeholder="Telefono" required>
            </label>

            <button class="btn primary" type="submit">Registrati</button>
        </fieldset>
    </form>
</div>

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
