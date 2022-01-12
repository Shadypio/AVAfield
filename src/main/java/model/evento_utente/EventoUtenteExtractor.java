/**
 * Questa classe di utility evita ridondanze nel codice di EventoUtenteDAO
 */

package model.evento_utente;

import model.evento.Evento;
import model.evento_utente.EventoUtente;
import model.utente.Utente;
import model.utils.ResultSetExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventoUtenteExtractor implements ResultSetExtractor<EventoUtente> {
    /**
     * Estrae i dati dell'evento dalla base di dati e li inserisce in un oggetto EventoUtente
     * @param rs il risultato della query
     * @return la partecipazione di uno specifico utente ad uno specifico evento
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public EventoUtente extract(ResultSet rs) throws SQLException, IOException {
        EventoUtente eu= new EventoUtente();
        Evento e=new Evento();
        e.setIdEvento(rs.getInt("eu.eve_fk"));
        Utente u=new Utente();
        u.setIdUtente(rs.getInt("eu.ute_fk"));
        eu.setUtente(u);
        eu.setEvento(e);
        return eu;
    }
}
