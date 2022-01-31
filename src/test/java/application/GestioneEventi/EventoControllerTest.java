package application.GestioneEventi;

import application.GestioneEventi.EventoController;
import model.evento.Evento;
import model.evento.EventoServiceImpl;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class EventoControllerTest {
    private EventoServiceImpl es;
    private StrutturaServiceImpl ss;
    private EventoController ec;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletContext context;

    @Before
    public void setUp(){
        es= Mockito.mock(EventoServiceImpl.class);
        ss=Mockito.mock(StrutturaServiceImpl.class);
        ec=new EventoController(es,ss);
        request=Mockito.mock(HttpServletRequest.class);
        response=Mockito.mock(HttpServletResponse.class);
        session=Mockito.mock(HttpSession.class);
        dispatcher=Mockito.mock(RequestDispatcher.class);
        context=Mockito.mock(ServletContext.class);
    }


    @Test
    public void nuovoEventoTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/nuovoEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(session.getAttribute("loggato")).thenReturn(true);
        when(request.getParameter("idStruttura")).thenReturn("ciao");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ec.doPost(request,response);
        verify(request,atLeastOnce()).setAttribute("idStruttura","ciao");
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void nuovoEventoNullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/nuovoEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Boolean b=null;
        when(session.getAttribute("loggato")).thenReturn(b);
        ec.doPost(request,response);
    }

    @Test
    public void listaEventiPerUserTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/listaPerPartecipare");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente utente=new Utente();
        utente.setAdmin(false);
        when(session.getAttribute("profilo")).thenReturn(utente);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ec.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void listaEventiPerAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/listaPerPartecipare");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente utente=new Utente();
        utente.setAdmin(true);
        when(session.getAttribute("profilo")).thenReturn(utente);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ec.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void viewEventiTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/viewEvents");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ec.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    /*@Test
    public void addEventoTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/addEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        ArrayList<Evento> listaEventi=new ArrayList<Evento>();
        when(es.visualizzaEventi()).thenReturn(listaEventi);
        when(listaEventi.size()).thenReturn(anyInt());
        when(request.getParameter("nome")).thenReturn(anyString());
        when(request.getParameter("numero")).thenReturn(anyString());
        when(request.getParameter("idStr")).thenReturn(anyString());
        when(request.getParameter("data")).thenReturn(anyString());
        when(request.getParameter("time")).thenReturn(anyString());
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ec.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }*/

    @Test
    public void deleteEventoTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/deleteEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        String idDelete="2";
        when(request.getParameter("selezioneDelete")).thenReturn(idDelete);
        ec.doPost(request,response);
        this.viewEventiTest();
    }

    @Test
    public void partecipaEventoNullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/partecipaAdEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Boolean b=null;
        when(session.getAttribute("loggato")).thenReturn(b);
        ec.doPost(request,response);
    }






   /* @Test
    public void partecipaEventoTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/partecipaAdEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(session.getAttribute("loggato")).thenReturn(true);
        when(request.getParameter("idEvento")).thenReturn(anyString());
        Evento e=new Evento();
        Utente u=new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);
        when(es.trovaEvento(anyInt())).thenReturn(e);


        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ec.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }*/



}
