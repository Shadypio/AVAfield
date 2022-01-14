/**
 * Questa classe si occupa di modellare le operazioni inerenti
 * alla gestione di profilo sia dell'utente admin che
 * dell'utente semplice
 */

package application.Autenticazione;

import model.evento.Evento;
import model.evento.EventoServiceImpl;
import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AutenticazioneController", value = "/ac/*")
public class AutenticazioneController extends HttpServlet {
    UtenteServiceImpl us;
    EventoServiceImpl es;
    StrutturaServiceImpl ss;

    public AutenticazioneController(){
        this.us=new UtenteServiceImpl(new UtenteDAO());
        this.es=new EventoServiceImpl();
        this.ss=new StrutturaServiceImpl();
    }

    public AutenticazioneController(UtenteServiceImpl us){
        this.us=us;
        this.es=new EventoServiceImpl();
        this.ss=new StrutturaServiceImpl();
    }

    public AutenticazioneController(UtenteServiceImpl us,EventoServiceImpl es){
        this.us=us;
        this.es=es;
        this.ss=new StrutturaServiceImpl();
    }

    public AutenticazioneController(UtenteServiceImpl us,EventoServiceImpl es, StrutturaServiceImpl ss){
        this.us=us;
        this.es=es;
        this.ss=ss;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=request.getServletContext().getContextPath();
        String email,pass;
        Utente log=new Utente(); //Utente che prova a loggarsi
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {

            /**
             * Reindirizza alla pagina di login dell'area riservata (admin)
             */
            case "/secret":
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/secret.jsp").forward(request, response);
                break;

            /**
             * Reindirizza alla dashboard dell'admin o dell'utente
             */
            case "/dashboard":
                Boolean loggato = (Boolean) session.getAttribute("loggato");
                if (loggato == null) {
                    email = request.getParameter("email");
                    pass = request.getParameter("password");
                    log = us.login(email, pass);
                    if(log == null){
                        session.setAttribute("failedAdmin",true);
                        response.sendRedirect(request.getContextPath() + "/ac/secret");
                        break;
                    }
                    loggato=false;
                }
                if (loggato)
                    log=(Utente) session.getAttribute("profilo");
                if (log.isAdmin()) {
                    session.setAttribute("numStrutture", ss.visualizzaStrutture().size());
                    session.setAttribute("numEventi", es.visualizzaEventi().size());
                    session.setAttribute("numUtenti", us.visualizzaUtenti().size());
                    session.setAttribute("profilo", log);
                    session.setAttribute("loggato", true);
                    request.getRequestDispatcher("/WEB-INF/interface/area_riservata/dashboard.jsp").forward(request, response);
                } else
                    request.getRequestDispatcher("/WEB-INF/interface/area_riservata/secret.jsp").forward(request, response);
                break;

            /**
             * Se l'utente non è ancora loggato, reindirizza alla pagina di
             * signin e di signup. Se l'utente è già loggato, reindirizza
             * alla pagina personale
             */
            case "/signin_signup": //pagina di registrazione e login
                loggato = (Boolean) session.getAttribute("loggato");
                Utente profilo = (Utente) session.getAttribute("profilo");
                if (loggato != null || profilo!=null) {
                    ArrayList<Evento> listaE= es.findAllEventi(profilo);
                    for(Evento e: listaE) {
                        Struttura s=ss.trovaStruttura(e.getStruttura().getIdStruttura());
                        e.setStruttura(s);
                    }
                    session.setAttribute("listaEventi",listaE);
                    request.getRequestDispatcher("/WEB-INF/interface/site/account.jsp").forward(request, response);
                }else
                    request.getRequestDispatcher("/WEB-INF/interface/site/signin_signup.jsp").forward(request, response);
                break;

            /**
             * Raccoglie i parametri di un nuovo utente
             * e ne effettua la registrazione sulla piattaforma
             */
            case "/create":
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String user = request.getParameter("username");
                email = request.getParameter("email");
                String tel = request.getParameter("telefono");
                pass = request.getParameter("password");
                String val = request.getParameter("autovalutazione");
                int auto = Integer.parseInt(val);
                int id = us.visualizzaUtenti().size()+1;
                if (us.checkEmail(email)) {
                    System.out.println(email);
                    session.setAttribute("emailUsed",true);
                    response.sendRedirect(address + "/ac/signin_signup");
                }else {
                    Utente nuovo = new Utente(nome, cognome, email, user, pass, tel, false, auto, id);
                    us.registrazione(nuovo);
                    request.getRequestDispatcher("/WEB-INF/interface/site/registered.jsp").forward(request, response);
                }
                break;

            /**
             * Si occupa del login dell'utente all'interno della piattaforma
             */
            case "/signin":
                email = request.getParameter("email");
                pass = request.getParameter("password");
                log = us.login(email, pass);
                profilo = (Utente) session.getAttribute("profilo");
                if (log != null || profilo!=null) {
                    if (log==null)
                        log=profilo;
                    ArrayList<Evento> listaE= es.findAllEventi(log);
                    for(Evento e: listaE) {
                        Struttura s=ss.trovaStruttura(e.getStruttura().getIdStruttura());
                        e.setStruttura(s);
                    }
                    session.setAttribute("listaEventi",listaE);
                    session.setAttribute("profilo", log);
                    session.setAttribute("loggato", true);
                    request.getRequestDispatcher("/WEB-INF/interface/site/account.jsp").forward(request, response);
                } else {
                    if (email == null)
                        session.setAttribute("failedUtente", false);
                    else {
                        if (log == null)
                            session.setAttribute("failedUtente", true);
                    }
                    request.getRequestDispatcher("/WEB-INF/interface/site/signin_signup.jsp").forward(request, response);
                }
                break;

            /**
             * Efrfettua il logout  dell'utente loggato
             */
            case "/logout":
                session.setAttribute("loggato", false);
                log= (Utente) session.getAttribute("profilo");
                if (log.isAdmin())
                    response.sendRedirect(address + "/ac/secret");
                else
                    response.sendRedirect(address + "/ac/signin_signup");
                session.removeAttribute("loggato");
                session.removeAttribute("profilo");
                session.invalidate();
                break;
        }
    }
}
