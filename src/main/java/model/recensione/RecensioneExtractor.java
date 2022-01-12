/**
 * Questa classe di utility evita ridondanze nel codice di RecensioneDAO
 */

package model.recensione;

import model.struttura.Struttura;
import model.utente.Utente;
import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneExtractor implements ResultSetExtractor<Recensione> {
    @Override
    public Recensione extract(ResultSet rs) throws SQLException, IOException {
        Recensione r= new Recensione();
        r.setIdRecensione(rs.getInt("rec.idRecensione"));
        r.setTitolo(rs.getString("rec.titolo"));
        r.setTesto(rs.getString("rec.testo"));
        r.setNumeroStelle(rs.getInt("rec.numeroStelle"));
        int idUtente= rs.getInt("rec.ute_fk");
        Utente u=new Utente();
        u.setIdUtente(idUtente);
        int idStruttura= rs.getInt("rec.str_fk");
        Struttura s=new Struttura();
        s.setIdStruttura(idStruttura);
        r.setStruttura(s);
        r.setUtente(u);
        return r;
    }
}
