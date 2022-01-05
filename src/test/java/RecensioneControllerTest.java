import application.GestioneEventi.EventoController;
import application.GestioneRecensioni.RecensioneController;
import application.GestioneStrutture.StrutturaController;
import model.evento.EventoServiceImpl;
import model.recensione.Recensione;
import model.recensione.RecensioneServiceImpl;
import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import model.utente.UtenteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class RecensioneControllerTest {
    private StrutturaServiceImpl ss;
    private UtenteServiceImpl us;
    private RecensioneServiceImpl rs;
    private RecensioneController rc;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private Recensione r;
    private Utente utente;

    @Before
    public void setUp() {
        ss = Mockito.mock(StrutturaServiceImpl.class);
        us = Mockito.mock(UtenteServiceImpl.class);
        rs = Mockito.mock(RecensioneServiceImpl.class);
        rc = new RecensioneController(rs, ss, us);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        r = Mockito.mock(Recensione.class);
        utente = Mockito.mock(Utente.class);
    }

    @Test
    public void addRecensioneEmptyTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/addRecensione");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente u = new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);
        when(request.getParameter("idStruttura")).thenReturn("1");
        when(request.getParameter("titolo")).thenReturn("ciao");
        when(request.getParameter("testo")).thenReturn("ciao1");
        String stelle = "5";
        when(request.getParameter("stelle")).thenReturn(stelle);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Struttura s = new Struttura();
        when(ss.trovaStruttura(1)).thenReturn(s);
        ArrayList<Recensione> listaRecensione = new ArrayList<>();
        when(rs.visualizzaRecensioniByIdStruttura(s)).thenReturn(listaRecensione);
        when(r.getUtente()).thenReturn(u);
        when(utente.getIdUtente()).thenReturn(3);
        rc.doPost(request, response);
    }

    @Test
    public void addRecensioneFullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/addRecensione");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente u = new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);
        when(request.getParameter("idStruttura")).thenReturn("1");
        when(request.getParameter("titolo")).thenReturn("ciao");
        when(request.getParameter("testo")).thenReturn("ciao1");
        String stelle = "5";
        when(request.getParameter("stelle")).thenReturn(stelle);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Struttura s = new Struttura();
        when(ss.trovaStruttura(1)).thenReturn(s);
        ArrayList<Recensione> listaRecensione = new ArrayList<>();
        listaRecensione.add(new Recensione(1, "Bel Test", "Veramente divertente", 5, u, s));
        when(rs.visualizzaRecensioniByIdStruttura(s)).thenReturn(listaRecensione);
        when(r.getUtente()).thenReturn(u);
        when(utente.getIdUtente()).thenReturn(3);
        rc.doPost(request, response);
    }
}
