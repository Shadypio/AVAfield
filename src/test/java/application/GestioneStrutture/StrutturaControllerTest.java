package application.GestioneStrutture;

import application.GestioneEventi.EventoController;
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

public class StrutturaControllerTest {
    private EventoServiceImpl es;
    private StrutturaServiceImpl ss;
    private StrutturaController sc;
    private UtenteServiceImpl us;
    private RecensioneServiceImpl rs;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private Struttura s;
    private Recensione r;

    @Before
    public void setUp(){
        es= Mockito.mock(EventoServiceImpl.class);
        ss=Mockito.mock(StrutturaServiceImpl.class);
        us=Mockito.mock(UtenteServiceImpl.class);
        rs=Mockito.mock(RecensioneServiceImpl.class);
        sc=new StrutturaController(ss, us, rs);
        request=Mockito.mock(HttpServletRequest.class);
        response=Mockito.mock(HttpServletResponse.class);
        session=Mockito.mock(HttpSession.class);
        dispatcher=Mockito.mock(RequestDispatcher.class);
        context=Mockito.mock(ServletContext.class);
        s=Mockito.mock(Struttura.class);
        r=Mockito.mock(Recensione.class);
    }

    @Test
    public void viewStructuresTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/viewStructures");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void deleteStrutturaTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/deleteStruttura");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String idDelete="2";
        when(request.getParameter("selezioneDelete")).thenReturn(idDelete);
        sc.doPost(request,response);
        this.viewStructuresTest();
    }

    @Test
    public void updateStrutturaWithParkOnTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/updateStruttura");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String idUpdate="2";
        when(request.getParameter("selezioneMod")).thenReturn(idUpdate);
        when(request.getParameter("nome")).thenReturn("ciao2");
        when(request.getParameter("cat")).thenReturn("ciao2");
        when(request.getParameter("desc")).thenReturn("ciao2");
        when(request.getParameter("indirizzo")).thenReturn("ciao2");
        when(request.getParameter("tel")).thenReturn("ciao2");
        when(request.getParameter("capienza")).thenReturn("2");
        when(request.getParameter("numSpo")).thenReturn("2");
        when(request.getParameter("park")).thenReturn("on");
        sc.doPost(request,response);
        this.viewStructuresTest();
    }

    @Test
    public void updateStrutturaWithParkNotTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/updateStruttura");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String idUpdate="2";
        when(request.getParameter("selezioneMod")).thenReturn(idUpdate);
        when(request.getParameter("nome")).thenReturn("ciao2");
        when(request.getParameter("cat")).thenReturn("ciao2");
        when(request.getParameter("desc")).thenReturn("ciao2");
        when(request.getParameter("indirizzo")).thenReturn("ciao2");
        when(request.getParameter("tel")).thenReturn("ciao2");
        when(request.getParameter("capienza")).thenReturn("2");
        when(request.getParameter("numSpo")).thenReturn("2");
        when(request.getParameter("park")).thenReturn("not");
        sc.doPost(request,response);
        this.viewStructuresTest();
    }

    @Test
    public void addStrutturaWithParkOnTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/addStruttura");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(request.getParameter("nome")).thenReturn("ciao2");
        when(request.getParameter("cat")).thenReturn("ciao2");
        when(request.getParameter("desc")).thenReturn("ciao2");
        when(request.getParameter("indirizzo")).thenReturn("ciao2");
        when(request.getParameter("tel")).thenReturn("ciao2");
        when(request.getParameter("capienza")).thenReturn("2");
        when(request.getParameter("numSpo")).thenReturn("2");
        when(request.getParameter("park")).thenReturn("on");
        sc.doPost(request,response);
        this.viewStructuresTest();
    }

    @Test
    public void addStrutturaWithParkNotTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/addStruttura");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(request.getParameter("nome")).thenReturn("ciao2");
        when(request.getParameter("cat")).thenReturn("ciao2");
        when(request.getParameter("desc")).thenReturn("ciao2");
        when(request.getParameter("indirizzo")).thenReturn("ciao2");
        when(request.getParameter("tel")).thenReturn("ciao2");
        when(request.getParameter("capienza")).thenReturn("2");
        when(request.getParameter("numSpo")).thenReturn("2");
        when(request.getParameter("park")).thenReturn("not");
        sc.doPost(request,response);
        this.viewStructuresTest();
    }

    @Test
    public void singleStrutturaWithNoRecensioniTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/singleStructure");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String idStruttura="2";
        when(request.getParameter("idStruttura")).thenReturn(idStruttura);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void singleStrutturaWithRecensioniTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/singleStructure");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String idStruttura="2";
        int id=2;
        Utente ute=new Utente();
        Struttura str=new Struttura();
        when(request.getParameter("idStruttura")).thenReturn(idStruttura);
        when(ss.trovaStruttura(id)).thenReturn(str);
        ArrayList<Recensione> listaRecensioni=new ArrayList<>();
        Recensione rec=new Recensione(1,"ciao","ciao",3,ute,str);
        listaRecensioni.add(rec);
        when(rs.visualizzaRecensioniByIdStruttura(str)).thenReturn(listaRecensioni);
        when(r.getUtente()).thenReturn(ute);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void viewStructuresUserWithNullCategoriaTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/viewStructuresUser");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String categoria=null;
        when(request.getParameter("categoria")).thenReturn(categoria);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void viewStructuresUserWithCategoriaTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/viewStructuresUser");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String categoria="cat";
        when(request.getParameter("categoria")).thenReturn(categoria);
        ArrayList<Struttura> listaStruttura=new ArrayList<>();
        Struttura str=new Struttura("ciao","ciao","ciao","ciao",2,"ciao",2,true,2);
        listaStruttura.add(str);
        when(ss.visualizzaStrutture()).thenReturn(listaStruttura);
        when(s.getCategoria()).thenReturn(categoria);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void viewStructuresUserWithSameCategoriaTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/viewStructuresUser");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String categoria="cat";
        when(request.getParameter("categoria")).thenReturn(categoria);
        ArrayList<Struttura> listaStruttura=new ArrayList<>();
        Struttura str=new Struttura("ciao","ciao","ciao","ciao",2,"ciao",2,true,2);
        listaStruttura.add(str);
        when(ss.visualizzaStrutture()).thenReturn(listaStruttura);
        when(s.getCategoria()).thenReturn(categoria);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

}

