package application.GestioneEventi;

import model.evento.Evento;
import model.evento.EventoServiceImpl;
import model.evento_utente.EventoUtente;
import model.evento_utente.EventoUtenteDAO;
import model.struttura.Struttura;
import model.struttura.StrutturaDAO;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Boolean verifica;
        String address=getServletContext().getContextPath();
        EventoServiceImpl es=new EventoServiceImpl();
        StrutturaServiceImpl ss=new StrutturaServiceImpl();
        Evento e =new Evento();//oggetto di appoggio
        Utente utente = new Utente();
        EventoUtenteDAO eventoUtenteDAO = new EventoUtenteDAO();
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
                session.setAttribute("listaEventi",es.visualizzaEventi());
                session.setAttribute("listaStrutture",ss.visualizzaStrutture());
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
                response.sendRedirect(address+"/ge/viewEvents");
                break;
            case "/deleteEvento":
                String idDelete=request.getParameter("selezioneDelete");
                e.setIdEvento(Integer.parseInt(idDelete));
                es.eliminaEvento(e);
                response.sendRedirect(address+"/ge/viewEvents");
                break;
            case "/addEventoUtente":
                e.setIdEvento(es.visualizzaEventi().size()+1);
                e.setNome(request.getParameter("nome"));
                e.setNumeroPartecipanti(Integer.parseInt(request.getParameter("numeroPartecipanti")));
                StrutturaDAO strutturaDAO = new StrutturaDAO();
                e.setStruttura(strutturaDAO.doRetrieveById(Integer.parseInt(request.getParameter("idStruttura"))));
                String dataEvento2=request.getParameter("dataEvento");
                String orarioEvento=request.getParameter("time");
                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat of2 = new SimpleDateFormat("HH:mm");
                df2.setLenient (false);
                of2.setLenient(false);
                Date dataEventoUtente,oraEventoUtente;
                try {
                    dataEventoUtente = df2.parse(dataEvento2);
                    oraEventoUtente= of2.parse(orarioEvento);
                    java.sql.Date sqlDate = new java.sql.Date(dataEventoUtente.getTime());
                    java.sql.Date sqlTime = new java.sql.Date(oraEventoUtente.getTime());
                    e.setDataEvento(sqlDate);
                    e.setOrario(sqlTime);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                utente = (Utente) session.getAttribute("profilo");
                eventoUtenteDAO.addEventoUtente(e, utente);
                es.creaEvento(e);
                request.getRequestDispatcher("/WEB-INF/interface/site/event_created.jsp").forward(request, response);
                break;
            case "/partecipaAdEvento":
                verifica= (Boolean) session.getAttribute("loggato");
                if (verifica==null)
                    response.sendRedirect(address+"/ac/signin");
                else {
                    utente = (Utente) session.getAttribute("profilo");
                    eventoUtenteDAO.addEventoUtente(e, utente);
                    request.getRequestDispatcher("/WEB-INF/interface/site/participated_to_event.jsp").forward(request, response);
                    break;
                }

        }

    }
}
