/**
 * Questa classe di utility evita ridondanze nel codice di StrutturaDAO
 */

package model.struttura;

import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StrutturaExtractor implements ResultSetExtractor<Struttura> {
    @Override
    public Struttura extract(ResultSet rs) throws SQLException, IOException {
        Struttura s=new Struttura();
        s.setIdStruttura(rs.getInt("str.idStruttura"));
        s.setNome(rs.getString("str.nome"));
        s.setIndirizzo(rs.getString("str.indirizzo"));
        s.setTelefono(rs.getString("str.telefono"));
        s.setDescrizione(rs.getString("str.descrizione"));
        s.setCapienza(rs.getInt("str.capienza"));
        s.setCategoria(rs.getString("str.categoria"));
        s.setNumeroSpogliatoi(rs.getInt("str.numeroSpogliatoi"));
        s.setParcheggio(rs.getBoolean("str.parcheggio"));
        return s;
    }
}
