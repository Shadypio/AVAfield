package application.GestioneEventi;

import model.evento.EventoServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EventoController", value = "/ge/*")
public class EventoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        EventoServiceImpl es=new EventoServiceImpl();
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewEvent":
                session.setAttribute("listaEventi",es.visualizzaEventi());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/events.jsp").forward(request, response);
                break;
        }

    }
}
