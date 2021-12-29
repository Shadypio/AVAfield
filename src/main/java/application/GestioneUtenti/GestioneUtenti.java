package application.GestioneUtenti;

import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GestioneUtenti", value = "/gu/*")
public class GestioneUtenti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        UtenteServiceImpl us=new UtenteServiceImpl();
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewUser":
                session.setAttribute("listaUtenti",us.visualizzaUtenti());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/users.jsp").forward(request, response);
                break;
        }

    }
}
