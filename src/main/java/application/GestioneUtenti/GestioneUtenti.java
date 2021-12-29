package application.GestioneUtenti;

import model.utente.Utente;
import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

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
        Utente u=new Utente();//oggetto di apppoggio
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/viewUser":
                session.setAttribute("listaUtenti",us.visualizzaUtenti());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/users.jsp").forward(request, response);
                break;
            case "/profileAdmin":
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/profile.jsp").forward(request, response);
                break;
            case "/updateAdmin":
                u.setIdUtente(Integer.parseInt(request.getParameter("idAdmin")));
                u.setNome(request.getParameter("nome"));
                u.setCognome(request.getParameter("cognome"));
                u.setEmail(request.getParameter("email"));
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("password"));
                u.setNumeroTelefono(request.getParameter("telefono"));
                u.setAdmin(true);
                Utente x= (Utente) session.getAttribute("profilo");
                if (x.getPassword().equals(u.getPassword()))
                    us.modificaDati(u);
                else
                    us.modificaDati2(u);
                session.setAttribute("profilo",u);
                response.sendRedirect(address+"/gu/profileAdmin");
                break;
            case "/addUtente":
                u.setIdUtente(us.visualizzaUtenti().size()+1);
                u.setNome(request.getParameter("nome"));
                u.setCognome(request.getParameter("cognome"));
                u.setEmail(request.getParameter("email"));
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("password"));
                u.setNumeroTelefono(request.getParameter("telefono"));
                String admin=request.getParameter("admin");
                if (admin.equals("on"))
                    u.setAdmin(true);
                else
                    u.setAdmin(false);
                u.setAutovalutazione(Integer.parseInt(request.getParameter("auto")));
                us.registrazione(u);
                response.sendRedirect(address+"/gu/viewUser");
                break;
            case "/deleteUtente":
                String idDelete=request.getParameter("selezioneDelete");
                u.setIdUtente(Integer.parseInt(idDelete));
                us.cancellazioneAccount(u);
                response.sendRedirect(address+"/gu/viewUser");
                break;
        }

    }
}
