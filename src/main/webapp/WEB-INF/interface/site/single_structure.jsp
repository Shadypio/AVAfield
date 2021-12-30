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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>


<form action="${pageContext.request.contextPath}/ge/nuovoEvento" method="post">
    <div>
        <input type="hidden" name="idStruttura" value="<%=struttura.getIdStruttura()%>">
        <div>
            <div class="electronic_img"><img src="../images/campo_img.png" width="100px" height="100px"></div>
        </div>
        <div>
            <h1><%=struttura.getNome()%></h1><br>
            <h2>Dove raggiungerci: </h2><h3> <%=struttura.getIndirizzo()%></h3><br>
            <h4>Chi siamo? </h4><h5> <%=struttura.getDescrizione()%></h5><br>
            <h4>Organizza i tuoi eventi di: </h4><h5> <%=struttura.getCategoria()%></h5><br>
            <h4>Ospiti: </h4> <h5> <%=struttura.getCapienza()%></h5><br>
            <h4>Numero Spogliatoi: </h4> <h5> <%=struttura.getNumeroSpogliatoi()%></h5><br>
            <h4>Parcheggio: </h4> <h5> <%=struttura.isParcheggio()%></h5><br>
            <h4>Contattaci: </h4> <h5> <%=struttura.getTelefono()%></h5><br>
            <button type="submit" class="butAdd btn primary">Crea un evento</button>
        </div>
    </div>
</form>

<div>
    <h3>Recensioni</h3>
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
        <%int i=0; ArrayList<Recensione> recensioni = (ArrayList<Recensione>) request.getSession().getAttribute("listaRecensioni");%>
        <c:forEach var="recensione" items="${listaRecensioni}">
            <%Recensione recensione = recensioni.get(i++);%>
            <tr>
                <td>${recensione.titolo}</td>
                <td>${recensione.numeroStelle}</td>
                <td>${recensione.testo}</td>
                <td>${(recensione.utente).idUtente}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<form action="<%=request.getContextPath()%>/gr/addRecensione" method="post">
    <div>
        <fieldset>
            <legend>Aggiungi Recensione</legend>
            <input type="hidden" name="idStruttura" value="<%=struttura.getIdStruttura()%>">
            <span> Titolo: </span> <input type="text"name="titolo" id="titolo" placeholder="Titolo" required> <br>
            <span> Stelle: </span> <input type="range" name="stelle" id="stelle" value="3" min="0" max="5" required><br>
            <span> Testo: <input type="text" name="testo" id="testo" placeholder="Testo" required> <br>
                <button type='submit'>Salva</button>
                <button type='button' id='annulla'>Annulla</button>
        </fieldset>
    </div>
</form>

</body>
</html>
