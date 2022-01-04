package application.GestioneEventi;

import model.evento.Evento;
import model.evento.EventoDAO;
import model.evento.EventoServiceImpl;
import model.evento_utente.EventoUtente;
import model.evento_utente.EventoUtenteDAO;
import model.struttura.Struttura;
import model.struttura.StrutturaDAO;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import model.utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EventoController", value = "/ge/*")
public class EventoController extends HttpServlet {
    private EventoServiceImpl es;
    private StrutturaServiceImpl ss;

    public EventoController() {
        es=new EventoServiceImpl();
        ss=new StrutturaServiceImpl();
    }

    public EventoController(EventoServiceImpl es,StrutturaServiceImpl ss) {
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
        Boolean verifica;
        String address=request.getServletContext().getContextPath();
        Evento e=new Evento();//oggetto di appoggio
        Utente utente;
        String path=(request.getPathInfo() != null) ? request.getPathInfo(): "/";
        switch (path) {
            case "/nuovoEvento":
                verifica= (Boolean) session.getAttribute("loggato");
                if (verifica==null)
                    response.sendRedirect(address+"/ac/signin");
                else {
                    request.setAttribute("idStruttura", request.getParameter("idStruttura"));
                    request.getRequestDispatcher("/WEB-INF/interface/site/new_event.jsp").forward(request, response);
                }
                break;
            case "/listaPerPartecipare":
                Utente u= (Utente) session.getAttribute("profilo");
                session.setAttribute("listaEventi",es.visualizzaEventi());
                session.setAttribute("listaStrutture",ss.visualizzaStrutture());
                if (u.isAdmin())
                    request.getRequestDispatcher("/WEB-INF/interface/area_riservata/events.jsp").forward(request, response);
                else
                request.getRequestDispatcher("/WEB-INF/interface/site/showEvents.jsp").forward(request, response);
                break;
            case "/viewEvents":
                session.setAttribute("listaEventi",es.visualizzaEventi());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/events.jsp").forward(request, response);
                break;
            case "/addEvento":
                e.setIdEvento(es.visualizzaEventi().size()+1);
                e.setNome(request.getParameter("nome"));
                e.setNumeroPartecipanti(Integer.parseInt(request.getParameter("numero")));
                Struttura s=new Struttura();
                s.setIdStruttura(Integer.parseInt(request.getParameter("idStr")));
                e.setStruttura(s);
                String data=request.getParameter("data");
                String orario=request.getParameter("time");
                System.out.println(data+"    "+orario);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat of = new SimpleDateFormat("HH:mm");
                df.setLenient (false);
                of.setLenient(false);
                Date dataEvento,oraEvento;
                try {
                    dataEvento = df.parse (data);
                    oraEvento= of.parse(orario);
                    System.out.println(dataEvento+"    "+oraEvento);
                    java.sql.Date sqlDate = new java.sql.Date(dataEvento.getTime());
                    java.sql.Date sqlTime = new java.sql.Date(oraEvento.getTime());
                    e.setDataEvento(sqlDate);
                    e.setOrario(sqlTime);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                es.creaEvento(e);
                request.getRequestDispatcher("/WEB-INF/interface/site/event_created.jsp").forward(request, response);
                break;
            case "/deleteEvento":
                String idDelete=request.getParameter("selezioneDelete");
                e.setIdEvento(Integer.parseInt(idDelete));
                es.eliminaEvento(e);
                response.sendRedirect(address+"/ge/viewEvents");
                break;
            case "/partecipaAdEvento":
                verifica= (Boolean) session.getAttribute("loggato");
                if (verifica==null)
                    response.sendRedirect(address+"/ac/signin");
                else {
                    e=es.trovaEvento(Integer.parseInt(request.getParameter("idEvento")));
                    utente = (Utente) session.getAttribute("profilo");
                    es.partecipaEvento(e,utente);
                    request.getRequestDispatcher("/WEB-INF/interface/site/participated_to_event.jsp").forward(request, response);
                    break;
                }
        }

    }
}
