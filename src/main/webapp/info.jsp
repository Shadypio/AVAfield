<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body{
            background-color: var(--cream);
        }
        .content{
            margin-top: 110px;
            z-index: -1;
        }
        .infotext {
            font-family: Raleway;
            color: var(--darkcream);
        }

        .ainfo:link, .ainfo:visited {
            color: var(--darkcream);
        }

        .ainfo:active {
            color: #c7c1b8;
        }

    </style>
</head>
<body>
<br><br>
<div class="content grid-y">
    <fieldset class="grid-y cell w50 login" style="z-index: -1">
        <legend>Obiettivo</legend>
        <p class="infotext">
            Il sito si pone come obiettivo quello di diventare un punto di riferimento tra le piattaforme di
            prenotazione. Prendendo spunto dalle features più interessanti dei siti competitors, AVAfield
            propone un’interfaccia semplice, minimale e funzionale sia dal punto di vista
            dell’utente, sia dal punto di vista amministrativo. All’interno del catalogo saranno presenti
            strutture sportive rivolte a un pubblico dinamico che cerca svago nello sport.
            Allo scopo di coinvolgere sia utenti che utilizzano smartphone sia utenti che utilizzano
            computer, il sito web è strutturato in modo responsive.
        </p>
    </fieldset>
    <fieldset class="grid-y cell w50 login">
        <legend>Contattaci</legend>
        <p class="infotext">Puoi trovarci su <a class="ainfo" target="_blank" href="https://www.instagram.com" methods="_blank">Instagram</a>
            e su <a class="ainfo" target="_blank" href="https://www.facebook.com">Facebook</a>.</p>
    </fieldset>
</div>
</body>
</html>
