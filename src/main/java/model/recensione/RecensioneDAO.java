/**
 * Questa classe modella le interazioni tra la classe Recensione e la base di dati. Sono previsti i metodi
 * principali delle operazioni CRUD
 */

package model.recensione;

import model.struttura.Struttura;
import model.utente.Utente;
import model.utils.ConPool;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class RecensioneDAO {
    /**
     * Inserisce una recensione all'interno della base di dati
     * @param r la recensione da inserire
     */
    public void addRecensione(Recensione r){
        Struttura s=r.getStruttura();
        Utente u=r.getUtente();
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

    /**
     * Recupera tutte le recensioni dalla base di dati
     * @return la lista di recensioni
     */
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

    /**
     * Recupera una recensione a partire da un identificativo
     * @param id l'identificativo da considerare
     * @return la recensione
     */
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

    /**
     * Apporta delle modifiche alla recensione selezionata
     * @param r la recensione da modificare
     */
    public void doChanges(Recensione r){
        Utente r1= r.getUtente();
        Struttura r2= r.getStruttura();
        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "UPDATE recensione rec SET rec.titolo='" + r.getTitolo() + "', " + "rec.testo='"+r.getTesto() + "', rec.numeroStelle='"+r.getNumeroStelle() +"'," +
                    "rec.ute_fk='"+r1.getIdUtente()+"', rec.str_fk='"+r2.getIdStruttura()+"' WHERE rec.idRecensione=" + r.getIdRecensione() + ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina una recensione dalla base di dati
     * @param id l'identificativo della recensione da eliminare
     */
    public void deleteById(int id){
        try (Connection con = ConPool.getConnection()) {
            String query ="DELETE FROM recensione AS rec WHERE rec.idRecensione = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Recupera le recensioni a partire da una struttura
     * @param s la struttura di cui si desidera conoscere le recensioni
     * @return la lista di recensioni della struttura
     */
    public ArrayList<Recensione> doRetrieveRecensioniWithIdStruttura(Struttura s){
        ArrayList<Recensione> result=new ArrayList<Recensione>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensione AS rec WHERE rec.str_fk=(?);");
            ps.setInt(1, s.getIdStruttura());
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
}
