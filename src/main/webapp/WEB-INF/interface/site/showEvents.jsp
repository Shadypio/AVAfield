<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.evento.Evento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.struttura.Struttura" %>

<%-- PAGINA CON LISTA DI EVENTI GIA PRESENTI VISUALIZZABILE DA TUTTI
     OGNI EVENTO PERò è PRENOTABILE DA REGISTRATI SOLAMENTE--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
<section>
    <%  int i=0;   ArrayList<Evento> eventi = (ArrayList<Evento>) request.getSession().getAttribute("listaEventi");%>
    <c:forEach items="${listaEventi}" var="evento" >
        <%Evento evento = eventi.get(i++);%>
        <div onclick="window.open('<%=request.getContextPath()%>/ge/partecipaAdEvento?idEvento=${evento.idEvento}', '_self');">
            <div>
                <h5>Nome:${evento.nome}</h5>
                <h6>Partecipanti Max:${evento.numeroPartecipanti}</h6>
                <h6>Data: ${evento.dataEvento}</h6>
            </div>
        </div>
    </c:forEach>
</section>
</body>
</html>
