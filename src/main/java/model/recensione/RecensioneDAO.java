package model.recensione;

import model.struttura.Struttura;
import model.utente.Utente;
import model.utils.ConPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecensioneDAO {
    public void addRecensione(Recensione r, Struttura s, Utente u){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) VALUES(?,?,?,?,?,?)");
            ps.setInt(1,r.getIdRecensione());
            ps.setString(2,r.getTitolo());
            ps.setString(3,r.getTesto());
            ps.setInt(4, r.getNumeroStelle());
            ps.setInt(5, u.getIdUtente() );
            ps.setInt(6, s.getIdStruttura());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
