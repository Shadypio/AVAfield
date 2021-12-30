<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.struttura.Struttura" %>
<%@ page import="java.util.ArrayList" %><%-- PAGINA CON LISTA DI STRUTTURE GIA PRESENTI VISUALIZZABILE DA TUTTI
     OGNI STRUTTURA è RECENSIONABILE DA REGISTRATI SOLAMENTE--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strutture</title>
</head>
<body>
<section>
    <%  int i=0;
        ArrayList<Struttura> strutture = (ArrayList<Struttura>) request.getSession().getAttribute("listaStrutture");%>
    <c:forEach items="${listaStrutture}" var="evento" >
        <%Struttura struttura = strutture.get(i++);%>
        <div onclick="window.open('<%=request.getContextPath()%>/ge/addEventoUtente?idStruttura=${struttura.idStruttura}', '_self');">
            <div>
                <h4><%=struttura.getNome() %>
                </h4><p><span style="color: #262626;"><%=struttura.getIndirizzo()%></span>
                </p>
                <div class="electronic_img"><img src="images/campo_img.png" width="100px" height="100px"></div>
            </div>
        </div>
    </c:forEach>
</section>
</body>
</html>
