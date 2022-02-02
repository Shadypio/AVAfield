/**
 * Questa classe si occupa di modellare tutte le operazioni inerenti alla gestione
 * degli eventi
 */

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
import moduloFIA.ComparatorCrescente;
import moduloFIA.ComparatorDecrescente;
import moduloFIA.LinearSearch;
import moduloFIA.UniformCostSearch;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@WebServlet(name = "EventoController", value = "/ge/*")
public class EventoController extends HttpServlet {
    private EventoServiceImpl es;
    private StrutturaServiceImpl ss;

    public EventoController() {
        es = new EventoServiceImpl();
        ss = new StrutturaServiceImpl();
    }

    public EventoController(EventoServiceImpl es, StrutturaServiceImpl ss) {
        this.es = es;
        this.ss = ss;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean verifica;
        String address = request.getServletContext().getContextPath();
        Evento e = new Evento();//oggetto di appoggio
        Utente utente;
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {

            /**
             * Reindirizza alla pagina di creazione di un nuovo evento.
             * Si effettua prima un controllo di login dell'utente
             */

            case "/nuovoEvento":
                verifica = (Boolean) session.getAttribute("loggato");
                if (verifica == null)
                    response.sendRedirect(address + "/ac/signin");
                else {
                    request.setAttribute("idStruttura", request.getParameter("idStruttura"));
                    request.getRequestDispatcher("/WEB-INF/interface/site/new_event.jsp").forward(request, response);
                }
                break;


            /**
             * Gestisce la visualizzazione degli eventi.
             * A seconda che la richiesta provenga dall'admin o da
             * un utente comune, la visualizzazione sarà diversa
             */
            case "/listaPerPartecipare":
                Utente u = (Utente) session.getAttribute("profilo");
                session.setAttribute("listaEventi", es.visualizzaEventi());
                session.setAttribute("listaStrutture", ss.visualizzaStrutture());
                if (u == null)
                    u = new Utente();
                if (u.isAdmin())
                    request.getRequestDispatcher("/WEB-INF/interface/area_riservata/events.jsp").forward(request, response);
                else
                    request.getRequestDispatcher("/WEB-INF/interface/site/showEvents.jsp").forward(request, response);
                break;

            /**
             * Gestisce la visualizzazione degli eventi.
             */
            case "/viewEvents":
                session.setAttribute("listaEventi", es.visualizzaEventi());
                request.getRequestDispatcher("/WEB-INF/interface/area_riservata/events.jsp").forward(request, response);
                break;

            /**
             * Raccoglie i valori inseriti dall'utente nel form e invoca il metodo
             * per inserire il nuovo evento nella base di dati
             */
            case "/addEvento":
                e.setIdEvento(es.visualizzaEventi().size() + 1);
                e.setNome(request.getParameter("nome"));
                e.setNumeroPartecipanti(Integer.parseInt(request.getParameter("numero")));
                Struttura s = new Struttura();
                s.setIdStruttura(Integer.parseInt(request.getParameter("idStr")));
                e.setStruttura(s);
                String data = request.getParameter("data");
                String orario = request.getParameter("time");
                java.sql.Date dataSQL = Date.valueOf(data);
                e.setDataEvento(dataSQL);
                String[] parts = orario.split(":");
                String part1 = parts[0]; //
                String part2 = parts[1]; //
                int h = Integer.parseInt(part1);
                int m = Integer.parseInt(part2);
                Time t = new Time(h, m, 00);
                e.setOrario(t);
                System.out.println(e.toString());
                System.out.println(e.getOrario() + "    " + e.getDataEvento());
                es.creaEvento(e);
                request.getRequestDispatcher("/WEB-INF/interface/site/event_created.jsp").forward(request, response);
                break;

            /**
             * Raccoglie l'identificativo dell'evento selezionato dall'utente
             * e lo elimina dalla base di dati
             */
            case "/deleteEvento":
                String idDelete = request.getParameter("selezioneDelete");
                e.setIdEvento(Integer.parseInt(idDelete));
                es.eliminaEvento(e);
                response.sendRedirect(address + "/ge/viewEvents");
                break;

            /**
             * Si occupa della partecipazione a un evento da parte di un utente.
             * Si effettua prima un controllo sul login dell'utente, per poi
             * verificare se l'utente è già iscritto all'evento
             */
            case "/partecipaAdEvento":
                verifica = (Boolean) session.getAttribute("loggato");
                if (verifica == null)
                    response.sendRedirect(address + "/ac/signin");
                else {
                    e = es.trovaEvento(Integer.parseInt(request.getParameter("idEvento")));
                    utente = (Utente) session.getAttribute("profilo");
                    EventoUtenteDAO eventoUtenteDAO = new EventoUtenteDAO();
                    ArrayList<EventoUtente> eventiUtente = eventoUtenteDAO.doRetrieveEventiWithIdUtente(utente.getIdUtente());
                    Boolean alreadyParticipated = false;
                    for (EventoUtente eu : eventiUtente) {
                        if (eu.getEvento().getIdEvento() == e.getIdEvento()) {
                            alreadyParticipated = true;
                        }
                    }
                    if (alreadyParticipated) {
                        request.getRequestDispatcher("/WEB-INF/interface/site/already_participating.jsp").forward(request, response);
                    } else {
                        es.partecipaEvento(e, utente);
                        request.getRequestDispatcher("/WEB-INF/interface/site/participated_to_event.jsp").forward(request, response);
                    }
                }
                break;

            /**
             * Modulo FIA
             * Si occupa di consigliare all'utente gli eventi migliori in base alla propria
             * autovalutazione attraverso l'algoritmo di Ricerca a Costo Uniforme
             */
            case "/consigliaEventiUniform":
                verifica = (Boolean) session.getAttribute("loggato");
                if (verifica == null)
                    response.sendRedirect(address + "/ac/signin");
                else {
                    ArrayList<Evento> listaEventi=es.visualizzaEventi();
                    Utente utente1 = (Utente) session.getAttribute("profilo");
                    UniformCostSearch ucs=new UniformCostSearch();
                    final long startSortTime = System.nanoTime();
                    if (utente1.getAutovalutazione()>2.5)
                        Collections.sort(listaEventi,new ComparatorDecrescente());
                    else
                        Collections.sort(listaEventi,new ComparatorCrescente());
                    final long endSortTime = System.nanoTime();
                    System.out.println("Sort time: " + (endSortTime - startSortTime)+"\n\n" );
                    //start
                    final long startTime = System.nanoTime();
                    ArrayList<Evento> result=ucs.search(listaEventi,utente1.getAutovalutazione());
                    //end
                    final long endTime = System.nanoTime();
                    //Report
                    System.out.println("Execution time: " + (endTime - startTime)+"\n\n" );
                    session.setAttribute("uniform",true);
                    session.setAttribute("listaEventi", result);
                    session.setAttribute("listaStrutture", ss.visualizzaStrutture());
                    request.getRequestDispatcher("/WEB-INF/interface/site/eventi_consigliati.jsp").forward(request, response);
                }
                break;
            /**
             * Modulo FIA
             * Si occupa di consigliare all'utente gli eventi migliori in base alla propria
             * autovalutazione attraverso l'algoritmo di Ricerca Lineare
             */
            case "/consigliaEventiLinear":
                verifica = (Boolean) session.getAttribute("loggato");
                if (verifica == null)
                    response.sendRedirect(address + "/ac/signin");
                else {
                    ArrayList<Evento> listaEventi=es.visualizzaEventi();
                    Utente utente1 = (Utente) session.getAttribute("profilo");
                    LinearSearch ls = new LinearSearch();
                    //start
                    final long startTime = System.nanoTime();
                    ArrayList<Evento> result=ls.search(listaEventi,utente1.getAutovalutazione());
                    //end
                    final long endTime = System.nanoTime();
                    //Report
                    System.out.println("Execution time: " + (endTime - startTime)+"\n\n" );
                    session.setAttribute("uniform",false);
                    session.setAttribute("listaEventi", result);
                    session.setAttribute("listaStrutture", ss.visualizzaStrutture());
                    request.getRequestDispatcher("/WEB-INF/interface/site/eventi_consigliati.jsp").forward(request, response);
                }
                break;
        }
    }

}

