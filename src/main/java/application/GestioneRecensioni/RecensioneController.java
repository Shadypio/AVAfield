package application.GestioneRecensioni;

import model.recensione.RecensioneServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecensioneController", value = "/gr/*")
public class RecensioneController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        RecensioneServiceImpl rs=new RecensioneServiceImpl();
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewReview":
                session.setAttribute("listaRecensioni",rs.visualizzaRecensioni());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/reviews.jsp").forward(request, response);
                break;
        }

    }
}
