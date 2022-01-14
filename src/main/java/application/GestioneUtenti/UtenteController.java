/**
 * Questa classe si occupa di modellare tutte le operazioni
 * inerenti agli utenti
 */

package application.GestioneUtenti;

import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GestioneUtenti", value = "/gu/*")
public class UtenteController extends HttpServlet {
    private UtenteServiceImpl us;

    public UtenteController(){
        us=new UtenteServiceImpl();
    }

    public UtenteController(UtenteServiceImpl us){
        this.us=us;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=request.getServletContext().getContextPath();
        Utente u=new Utente();//oggetto di apppoggio
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {

            /**
             * Gestisce la visualizzazione di tutti gli utenti
             */
            case "/viewUsers":
                session.setAttribute("listaUtenti",us.visualizzaUtenti());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/users.jsp").forward(request, response);
                break;

            /**
             * Reindirizza alla pagina di gestione del proprio profilo utente
             */
            case "/profileAdmin":
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/profile.jsp").forward(request, response);
                break;

            /**
             * Raccoglie le modifiche della gestione profilo e
             * apporta le modifiche richieste
             */
            case "/updateAdmin":
                u.setIdUtente(Integer.parseInt(request.getParameter("id")));
                u.setNome(request.getParameter("nome"));
                u.setCognome(request.getParameter("cognome"));
                u.setEmail(request.getParameter("email"));
                u.setUsername(request.getParameter("username"));
                String nuovaPass=request.getParameter("password");
                u.setNumeroTelefono(request.getParameter("telefono"));
                u.setAdmin(true);
                Utente x= us.trovaUtente(u.getIdUtente());
                String vecchiaPass=x.getPassword();
                if (vecchiaPass.equals(nuovaPass)) {
                    us.modificaDati(u);
                }else{
                    u.setPassword(nuovaPass);
                    us.modificaDati2(u);
                }
                session.setAttribute("profilo",u);
                response.sendRedirect(address+"/gu/profileAdmin");
                break;

            /**
             * Raccoglie i dati di un nuovo utente e lo inserisce
             * nella base di dati
             */
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
                response.sendRedirect(address+"/gu/viewUsers");
                break;

            /**
             * Raccoglie l'identificativo dell'utente selezionato e lo elimina
             * dalla base di dati
             */
            case "/deleteUtente":
                String idDelete=request.getParameter("selezioneDelete");
                u.setIdUtente(Integer.parseInt(idDelete));
                us.cancellazioneAccount(u);
                response.sendRedirect(address+"/gu/viewUsers");
                break;

            /**
             * Raccoglie le modifiche apportate all'utente e lo modifica
             */
            case "/updateUtente":
                u.setIdUtente(Integer.parseInt(request.getParameter("id")));
                u.setNome(request.getParameter("nome"));
                u.setCognome(request.getParameter("cognome"));
                u.setEmail(request.getParameter("email"));
                u.setUsername(request.getParameter("username"));
                nuovaPass=request.getParameter("password");
                u.setNumeroTelefono(request.getParameter("telefono"));
                u.setAdmin(false);
                x= us.trovaUtente(u.getIdUtente());
                vecchiaPass=x.getPassword();
                if (vecchiaPass.equals(nuovaPass)) {
                    us.modificaDati(u);
                }else{
                    u.setPassword(nuovaPass);
                    us.modificaDati2(u);
                }
                session.setAttribute("profilo",u);
                request.getRequestDispatcher("/WEB-INF/interface/site/account.jsp").forward(request, response);
                break;
        }

    }
}
