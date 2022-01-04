import application.GestioneEventi.EventoController;
import application.GestioneStrutture.StrutturaController;
import model.evento.EventoServiceImpl;
import model.recensione.RecensioneServiceImpl;
import model.struttura.Struttura;
import model.struttura.StrutturaServiceImpl;
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
        when(request.getParameter("selezionaDelete")).thenReturn("ciao");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        sc.doPost(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }
}
