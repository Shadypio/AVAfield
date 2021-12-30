package application.Autenticazione;

import model.evento.Evento;
import model.evento.EventoServiceImpl;
import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import model.utente.UtenteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AutenticazioneController", value = "/ac/*")
public class AutenticazioneController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String address=getServletContext().getContextPath();
        UtenteServiceImpl us=new UtenteServiceImpl();
        EventoServiceImpl es=new EventoServiceImpl();
        StrutturaServiceImpl ss=new StrutturaServiceImpl();
        String email,pass;
        Utente log=new Utente(); //Utente che prova a loggarsi
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/secret":// pagina di login admin
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/secret.jsp").forward(request, response);
                break;
            case "/dashboard": // admin tentativo di login to dashboard
                Boolean loggato = (Boolean) session.getAttribute("loggato");
                if (loggato == null) {
                    email = request.getParameter("email");
                    pass = request.getParameter("password");
                    log = us.login(email, pass);
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
            case "/signin_signup": //pagina di registrazione e login
                loggato = (Boolean) session.getAttribute("loggato");
                Utente profilo = (Utente) session.getAttribute("profilo");
                if (loggato != null || profilo!=null)
                    request.getRequestDispatcher("/WEB-INF/interface/site/account.jsp").forward(request, response);
                 else
                    request.getRequestDispatcher("/WEB-INF/interface/site/signin_signup.jsp").forward(request, response);
                break;
            case "/create": //registrazione nuovo utente
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String user = request.getParameter("username");
                email = request.getParameter("email");
                String tel = request.getParameter("telefono");
                pass = request.getParameter("password");
                String val = request.getParameter("autovalutazione");
                int auto = Integer.parseInt(val);
                int id = us.visualizzaUtenti().size()+1;
                Utente nuovo = new Utente(nome, cognome, email, user, pass, tel, false, auto, id);
                us.registrazione(nuovo);
                request.getRequestDispatcher("/WEB-INF/interface/site/registered.jsp").forward(request, response);
                break;
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
                } else
                    request.getRequestDispatcher("/WEB-INF/interface/site/signin_signup.jsp").forward(request, response);
                break;
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
