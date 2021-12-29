package application.GestioneStrutture;

import model.struttura.StrutturaServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StrutturaController", value = "/gs/*")
public class StrutturaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        StrutturaServiceImpl ss=new StrutturaServiceImpl();
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewStructure":
                session.setAttribute("listaStrutture",ss.visualizzaStruttura());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/structures.jsp").forward(request, response);
                break;
        }

    }
}
