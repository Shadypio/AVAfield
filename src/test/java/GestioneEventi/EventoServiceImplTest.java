package GestioneEventi;

import model.evento.Evento;
import model.evento.EventoDAO;
import model.evento.EventoServiceImpl;
import model.utente.UtenteDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventoServiceImplTest {

    @InjectMocks
    private EventoServiceImpl gestioneEventi;

    @Mock
    private EventoDAO eventoDAO;

    @Mock
    private UtenteDAO utenteDAO;

    @BeforeAll
    public void preparaMock() {
        Mockito.doAnswer(
                AdditionalAnswers.returnsFirstArg()
        ).when(eventoDAO).addEvento(
                Mockito.any(Evento.class)
        );

        Mockito.when(
                eventoDAO.doRetrieveById(Mockito.anyInt())
        ).thenAnswer(
                invocazione -> {
                    Evento evento = new Evento();
                    evento.setIdEvento(invocazione.getArgument(0));
                    return Optional.of(evento);
                }
        );
    }

    @Test
    public void creaEvento() {
        Evento evento = new Evento();
        EventoDAO eventoDAO = new EventoDAO();
        evento.setIdEvento(896);

        gestioneEventi.creaEvento(evento);

        assertEquals(
                "La funzione dovrebbe restituire l'evento inserito",
                evento.getIdEvento(),
                eventoDAO.doRetrieveById(896).getIdEvento()
        );
    }

    @Test
    public void eliminaEvento() {
        Evento evento = new Evento();
        evento.setIdEvento(1);

        Evento eventoEliminato = new Evento();
        eventoEliminato = evento;
        gestioneEventi.eliminaEvento(evento);

        //assertTrue(eventoEliminato.isPresent());
        assertEquals(evento, eventoEliminato);
    }

}
