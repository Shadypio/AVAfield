<%@ page import="model.struttura.Struttura" %><%--
  Created by IntelliJ IDEA.
  User: Enzuc
  Date: 30/12/2021
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%Struttura struttura = (Struttura) request.getAttribute("struttura");%>
<head>
    <title><%=struttura.getNome()%>
    </title>
</head>
<body>


<form action="${pageContext.request.contextPath}/ge/nuovoEvento" method="post">
    <div>
        <div>
            <input type="hidden" name="id" value="<%=struttura.getIdStruttura()%>">
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
    </div>
</form>
</body>
</html>
