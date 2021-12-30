<%-- DASHBOARD DI CONTROLLO DI ADMIN CON LINK VERSO LE LISTE DI UTENTI/STRUTTURE/EVENTI--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main>
    <aside class="sidebar" id="sideBar">
        <nav>
            <img src="<%=request.getContextPath()%>/images/avalogo.png" width="100" height="115">
            <a href="<%=request.getContextPath()%>/ac/dashboard">Dashboard</a>
            <a href="<%=request.getContextPath()%>/gu/profileAdmin">Profilo</a>
            <a href="<%=request.getContextPath()%>/gs/viewStructures">Gestione Strutture</a>
            <a href="<%=request.getContextPath()%>/gu/viewUsers">Gestione Utenti</a>
            <a href="<%=request.getContextPath()%>/ge/viewEvents">Gestione Eventi</a>
            <a href="<%=request.getContextPath()%>/ac/logout">Logout</a>
        </nav>
    </aside>
    <section id="main">
        <div>
            <div>
                <button class="openbtn" onclick="toggleNav()"><img src="<%=request.getContextPath()%>/images/toggle-icon.png">
                </button>
            </div>
            <div class="dash" onclick="window.open('<%=request.getContextPath()%>/gs/viewStructures', '_self');">
                <h4>Gestione Strutture</h4>
                <p>N° Strutture: <%=session.getAttribute("numStrutture")%>
                </p>
            </div>
            <div class="dash" onclick="window.open('<%=request.getContextPath()%>/gu/viewUsers', '_self');">
                <h4>Gestione Utenti</h4>
                <p>N° Utenti: <%=session.getAttribute("numUtenti")%>
                </p>
            </div>
            <div class="dash" onclick="window.open('<%=request.getContextPath()%>/ge/viewEvents', '_self');">
                <h4>Gestione Eventi</h4>
                <p>N° Eventi: <%=session.getAttribute("numEventi")%>
                </p>
            </div>

        </div>
    </section>
</main>


<script>
    let status = false;

    function toggleNav() {
        if (status) {
            document.getElementById("sideBar").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
            status = false;
        } else {
            document.getElementById("sideBar").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
            status = true;
        }
    }

    function modify() {
        document.getElementsByTagName("input").readOnly = true
    }
</script>


</body>
</html>
