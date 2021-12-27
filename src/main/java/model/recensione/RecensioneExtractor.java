package model.recensione;

import model.ResultSetExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneExtractor implements ResultSetExtractor<Recensione> {
    @Override
    public Recensione extract(ResultSet rs) throws SQLException, IOException {
        return null;
    }
}
