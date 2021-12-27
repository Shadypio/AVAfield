package model.utente;

import model.utils.ResultSetExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtractor implements ResultSetExtractor<Utente> {
    @Override
    public Utente extract(ResultSet rs) throws SQLException, IOException {
        return null;
    }
}
