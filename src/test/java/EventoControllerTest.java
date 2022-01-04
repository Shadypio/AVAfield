import application.GestioneEventi.EventoController;
import model.evento.EventoServiceImpl;
import model.struttura.StrutturaServiceImpl;
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
}
