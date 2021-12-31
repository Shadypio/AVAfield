<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.struttura.Struttura" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- PAGINA CON LISTA DI STRUTTURE GIA PRESENTI VISUALIZZABILE DA TUTTI
     OGNI STRUTTURA è RECENSIONABILE DA REGISTRATI SOLAMENTE--%>

<html>
<head>
    <title>Strutture</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/index.jsp">Torna alla Home</a>
<section>
    <%  int i=0;
        ArrayList<Struttura> strutture = (ArrayList<Struttura>) request.getSession().getAttribute("listaStrutture");%>
    <c:forEach items="${listaStrutture}" var="evento" >
        <%Struttura struttura = strutture.get(i++);%>
        <div onclick="window.open('<%=request.getContextPath()%>/gs/singleStructure?idStruttura=<%=struttura.getIdStruttura()%>', '_self');">
            <div>
                <h4><%=struttura.getNome() %>
                </h4><p><span style="color: #262626;"><%=struttura.getIndirizzo()%></span>
                </p>
                <div class="electronic_img"><img src="../images/campo_img.png" width="100px" height="100px"></div>
            </div>
        </div>
    </c:forEach>
</section>
</body>
</html>
