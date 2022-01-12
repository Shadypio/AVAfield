/**
 * Questa classe di utility evita ridondanze nel codice di UtenteDAO
 */

package model.utente;

import model.utils.ResultSetExtractor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtractor implements ResultSetExtractor<Utente> {

    /**
     * Estrae i dati dell'utente dalla base di dati e li inserisce in un oggetto Utente
     * @param rs il risultato della query
     * @return l'utente della base di dati sotto forma di oggetto
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public Utente extract(ResultSet rs) throws SQLException, IOException {
        Utente u= new Utente();
        u.setIdUtente(rs.getInt("ute.idUtente"));
        u.setNome(rs.getString("ute.nome"));
        u.setCognome(rs.getString("ute.cognome"));
        u.setEmail(rs.getString("ute.email"));
        u.setUsername(rs.getString("ute.username"));
        u.setPasswordNOSHA(rs.getString("ute.password"));
        u.setNumeroTelefono(rs.getString("ute.telefono"));
        u.setAutovalutazione(rs.getInt("ute.autovalutazione"));
        u.setAdmin(rs.getBoolean("ute.isAdmin"));
        return u;
    }
}
