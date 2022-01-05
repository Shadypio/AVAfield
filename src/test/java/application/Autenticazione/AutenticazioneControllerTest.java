package application.Autenticazione;

import application.Autenticazione.AutenticazioneController;
import application.GestioneEventi.EventoController;
import model.evento.Evento;
import model.evento.EventoServiceImpl;
import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;
import model.utente.Utente;
import model.utente.UtenteServiceImpl;
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
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AutenticazioneControllerTest {

    private UtenteServiceImpl us;
    private EventoServiceImpl es;
    private StrutturaServiceImpl ss;
    private AutenticazioneController ac;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletContext context;

    @Before
    public void setUp(){
        us=Mockito.mock(UtenteServiceImpl.class);
        es=Mockito.mock(EventoServiceImpl.class);
        ss=Mockito.mock(StrutturaServiceImpl.class);
        ac=new AutenticazioneController(us, es, ss);
        request=Mockito.mock(HttpServletRequest.class);
        response=Mockito.mock(HttpServletResponse.class);
        session=Mockito.mock(HttpSession.class);
        dispatcher=Mockito.mock(RequestDispatcher.class);
        context=Mockito.mock(ServletContext.class);
    }

    @Test
    public void secretTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/secret");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ac.doPost(request,response);
    }

    @Test
    public void dashboardLoggatoIsNullTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/dashboard");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        Boolean bool = null;
        when(session.getAttribute("loggato")).thenReturn(bool);
        when(request.getParameter("email")).thenReturn("ciao3");
        when(request.getParameter("password")).thenReturn("ciao4");
        Utente u = new Utente();
        when(us.login("ciao3", "ciao4")).thenReturn(u);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        ac.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void dashboardLoggatoNotNullUtenteIsAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/dashboard");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        when(session.getAttribute("loggato")).thenReturn(true);
        Utente u = new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);
        u.setAdmin(true);
        ArrayList<Struttura> arrayListStrutture = new ArrayList<>();
        ArrayList<Evento> arrayListEventi = new ArrayList<>();
        ArrayList<Utente> arrayListUtente = new ArrayList<>();
        when(ss.visualizzaStrutture()).thenReturn(arrayListStrutture);
        when(es.visualizzaEventi()).thenReturn(arrayListEventi);
        when(us.visualizzaUtenti()).thenReturn(arrayListUtente);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        ac.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void signInSignUpTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/signin_signup");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        Boolean bool = true;
        when(session.getAttribute("loggato")).thenReturn(bool);
        Utente u = new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);

        ArrayList<Evento> arrayListEvento = new ArrayList<>();
        when(es.findAllEventi(u)).thenReturn(arrayListEvento);

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ac.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void createTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/create");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        when(request.getParameter("nome")).thenReturn("ciao3");
        when(request.getParameter("cognome")).thenReturn("ciao4");
        when(request.getParameter("username")).thenReturn("ciao5");
        when(request.getParameter("email")).thenReturn("ciao6");
        when(request.getParameter("telefono")).thenReturn("ciao7");
        when(request.getParameter("password")).thenReturn("ciao8");
        String val = "2";
        when(request.getParameter("autovalutazione")).thenReturn(val);

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ac.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);

    }

    @Test
    public void signInTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/signin");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        when(request.getParameter("email")).thenReturn("ciao3");
        when(request.getParameter("password")).thenReturn("ciao4");

        Utente u = new Utente();
        Utente u2 = new Utente();
        when(us.login("ciao3", "ciao4")).thenReturn(u);
        when(session.getAttribute("profilo")).thenReturn(u2);

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        ac.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    public void logoutTestIsAdmin() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/logout");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        Utente u = new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);
        u.setAdmin(true);
        this.secretTest();

        session.removeAttribute("loggato");
        session.removeAttribute("profilo");
        session.invalidate();
        session.invalidate();
    }

    @Test
    public void logoutTestIsNotAdmin() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/logout");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");

        Utente u = new Utente();
        when(session.getAttribute("profilo")).thenReturn(u);
        u.setAdmin(false);
        this.signInSignUpTest();

        session.removeAttribute("loggato");
        session.removeAttribute("profilo");
        session.invalidate();
        session.invalidate();
    }

}
