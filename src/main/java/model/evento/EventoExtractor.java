/**
 * Questa classe di utility evita ridondanze nel codice di EventoDAO
 */

package model.evento;

import model.struttura.Struttura;
import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventoExtractor implements ResultSetExtractor<Evento>{

    /**
     * Estrae i dati dell'evento dalla base di dati e li inserisce in un oggetto Evento
     * @param rs il risultato della query
     * @return l'evento della base di dati sotto forma di oggetto
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public Evento extract(ResultSet rs) throws SQLException, IOException {
        Evento e = new Evento();
        e.setIdEvento(rs.getInt("eve.idEvento"));
        e.setNome(rs.getString("eve.nome"));
        e.setNumeroPartecipanti(rs.getInt("eve.numeroPartecipanti"));
        e.setDataEvento(rs.getDate("eve.dataEvento"));
        e.setOrario(rs.getTime("eve.orario"));
        e.setMedia(rs.getDouble("eve.media"));
        int idStruttura=rs.getInt("eve.str_fk");
        Struttura s=new Struttura();
        s.setIdStruttura(idStruttura);
        e.setStruttura(s);
        return e;
    }
}
