package model.recensione;

import model.struttura.Struttura;
import model.utente.Utente;
import model.utente.UtenteExtractor;
import model.utils.ConPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Recensione> doRetrieveAll(){
        ArrayList<Recensione> result=new ArrayList<Recensione>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione AS rec");
            ResultSet rs = ps.executeQuery();
            RecensioneExtractor recExt = new RecensioneExtractor();
            while(rs.next()) {
                result.add(recExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Recensione doRetrieveById(int id){
        Recensione r= new Recensione();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione AS rec WHERE idRecensione=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            RecensioneExtractor recExt= new RecensioneExtractor();
            if (rs.next())
                r=recExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return r;
    }

    public void doChanges(Recensione r){
        Utente r1= r.getUtente();
        Struttura r2= r.getStruttura();
        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "UPDATE recensione rec SET rec.titolo='" + r.getTitolo() + "', " + "rec.testo='"+r.getTesto() + "', rec.numeroStelle='"+r.getNumeroStelle() +"'," +
                    "rec.ute_fk='"+r1.getIdUtente()+"', ute.str_fk='"+r2.getIdStruttura()+"' WHERE rec.idRecensione=" + r.getIdRecensione() + ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
