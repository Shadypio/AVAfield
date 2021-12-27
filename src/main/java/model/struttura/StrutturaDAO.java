package model.struttura;



import model.utils.ConPool;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class StrutturaDAO {
    public void addStruttura(Struttura s) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, s.getIdStruttura());
            ps.setString(2, s.getNome());
            ps.setString(3, s.getIndirizzo());
            ps.setString(4, s.getTelefono());
            ps.setString(5, s.getDescrizione());
            ps.setInt(6, s.getCapienza());
            ps.setString(7, s.getCategoria());
            ps.setInt(8, s.getNumeroSpogliatoi());
            ps.setBoolean(9, s.isParcheggio());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean doChanges(Struttura s){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE struttura s SET s.nome = (?), s.indirizzo = (?), s.telefono =(?), s.descrizione=(?), s.capienza=(?), s.categoria=(?), s.numeroSpogliatoi=(?), s.parcheggio=(?) WHERE s.idStruttura=(?);");
            ps.setString(1,s.getNome());
            ps.setString(2,s.getIndirizzo());
            ps.setString(3, s.getTelefono());
            ps.setString(4, s.getDescrizione());
            ps.setInt(5, s.getCapienza());
            ps.setString(6,s.getCategoria());
            ps.setInt(7,s.getNumeroSpogliatoi());
            ps.setBoolean(8,s.isParcheggio());
            ps.setInt(9,s.getIdStruttura());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            return true;
        } catch(SQLException ex){
            return false;
        }
    }

    public ArrayList<Struttura> doRetrieveAll() {
        ArrayList<Struttura> result = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM struttura as str");
            ResultSet rs = ps.executeQuery();
            StrutturaExtractor strExt = new StrutturaExtractor();
            while (rs.next()) {
                result.add(strExt.extract(rs));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Struttura doRetrieveById(int id){
        Struttura s = new Struttura();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM struttura AS str WHERE idStruttura=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            StrutturaExtractor strExt= new StrutturaExtractor();
            if (rs.next())
                s=strExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return s;
    }
}

