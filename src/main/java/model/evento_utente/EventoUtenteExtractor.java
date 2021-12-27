package model.evento_utente;

import model.evento.Evento;
import model.evento_utente.EventoUtente;
import model.utente.Utente;
import model.utils.ResultSetExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventoUtenteExtractor implements ResultSetExtractor<EventoUtente> {
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
