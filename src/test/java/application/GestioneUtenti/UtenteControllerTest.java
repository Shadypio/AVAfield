package application.GestioneUtenti;

import application.GestioneRecensioni.RecensioneController;
import application.GestioneUtenti.UtenteController;
import model.recensione.Recensione;
import model.recensione.RecensioneServiceImpl;
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
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UtenteControllerTest {
        private StrutturaServiceImpl ss;
        private UtenteServiceImpl us;
        private RecensioneServiceImpl rs;
        private RecensioneController rc;
        private HttpServletResponse response;
        private HttpServletRequest request;
        private HttpSession session;
        private RequestDispatcher dispatcher;
        private ServletContext context;
        private UtenteController uc;
        private Utente utente;
        private ArrayList<Utente> lista;


    @Before
    public void setUp() {
        ss = Mockito.mock(StrutturaServiceImpl.class);
        us = Mockito.mock(UtenteServiceImpl.class);
        rs = Mockito.mock(RecensioneServiceImpl.class);
        uc = new UtenteController(us);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        context = Mockito.mock(ServletContext.class);
        utente = Mockito.mock(Utente.class);
        lista = Mockito.mock(ArrayList.class);

    }

    @Test
    public void viewUserTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/viewUsers");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        ArrayList<Utente> lista = new ArrayList<>();
        when(us.visualizzaUtenti()).thenReturn(lista);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        uc.doPost(request, response);
    }

    @Test
    public void profileAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/profileAdmin");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        uc.doPost(request, response);
    }

    @Test
    public void updateAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/updateAdmin");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente x = new Utente();
        x.setPassword("password");
        String id = "2";
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("nome")).thenReturn("nome");
        when(request.getParameter("cognome")).thenReturn("cognome");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("telefono")).thenReturn("telefono");
        when(us.trovaUtente(anyInt())).thenReturn(x);
        String vecchiaPass = "password";
        when(utente.getPassword()).thenReturn(vecchiaPass);
        uc.doPost(request, response);
        this.profileAdminTest();
    }

    @Test
    public void addUtenteTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/addUtente");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente x = new Utente();
        when(lista.size()).thenReturn(10);
        when(request.getParameter("nome")).thenReturn("nome");
        when(request.getParameter("cognome")).thenReturn("cognome");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("telefono")).thenReturn("telefono");
        when(request.getParameter("admin")).thenReturn("admin");
        String auto = "3";
        when(request.getParameter("auto")).thenReturn(auto);
        uc.doPost(request, response);
        this.viewUserTest();
    }

    @Test
    public void deleteUtenteTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/deleteUtente");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente u = new Utente();
        String idDelete = "10";
        when(request.getParameter("selezioneDelete")).thenReturn(idDelete);
        uc.doPost(request, response);
        this.viewUserTest();
    }

   /* @Test
   public void updateUtenteEqualsTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/updateUtente");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente x = new Utente();
        x.setPassword("vecchia");
        String id = "2";
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("nome")).thenReturn("nome");
        when(request.getParameter("cognome")).thenReturn("cognome");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("username")).thenReturn("username");
        String nuovaPass = "vecchia";
        when(request.getParameter("password")).thenReturn(nuovaPass);
        when(request.getParameter("telefono")).thenReturn("telefono");
        when(us.trovaUtente(anyInt())).thenReturn(x);
        String vecchiaPass = "vecchia";
        when(utente.getPassword()).thenReturn(vecchiaPass);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        uc.doPost(request, response);
    }*/

    @Test
    public void updateUtenteNoEqualsTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/updateUtente");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        Utente x = new Utente();
        x.setPassword("password");
        String id = "2";
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("nome")).thenReturn("nome");
        when(request.getParameter("cognome")).thenReturn("cognome");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("telefono")).thenReturn("telefono");
        when(us.trovaUtente(anyInt())).thenReturn(x);
        String vecchiaPass = "vecchia";
        when(utente.getPassword()).thenReturn(vecchiaPass);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        uc.doPost(request, response);
    }




}