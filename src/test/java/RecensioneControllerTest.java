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
    }

    @Test
    public void addRecensioneTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/nuovoEvento");
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("ciao2");
        when(session.getAttribute("profilo")).thenReturn(any(Utente.class));
        when(request.getParameter("idStruttura")).thenReturn("ciao");
        when(request.getParameter("titolo")).thenReturn("ciao");
        when(request.getParameter("testo")).thenReturn("ciao");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        rc.doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("idStruttura", "ciao");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}
