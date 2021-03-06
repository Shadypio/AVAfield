/**
 * Questa classe modella le interazioni tra la classe Evento e la base di dati. Sono previsti i metodi
 * principali delle operazioni CRUD
 */

package model.evento;

import model.utils.ConPool;
import model.struttura.Struttura;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class EventoDAO {

    /**
     * Inserisce un evento all'interno della base di dati
     * @param e l'evento da inserire
     */
    public void addEvento(Evento e){
        Struttura s=e.getStruttura();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, media, str_fk) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1,e.getIdEvento());
            ps.setString(2,e.getNome());
            ps.setInt(3,e.getNumeroPartecipanti());
            ps.setDate(4, (Date) e.getDataEvento());
            ps.setTime(5, (Time) e.getOrario());
            ps.setDouble(6,e.getMedia());
            ps.setInt(7, s.getIdStruttura());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Apporta delle modifiche all'evento selezionato
     * @param e l'evento da modificare
     * @return l'esito della modifica
     */
    public boolean doChanges(Evento e){
        Struttura s=e.getStruttura();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE evento e SET e.nome = (?), e.numeroPartecipanti = (?), e.dataEvento =(?), e.orario=(?), e.media=(?), e.str_fk=(?) WHERE e.idEvento = (?);");
            ps.setString(1,e.getNome());
            ps.setInt(2,e.getNumeroPartecipanti());
            ps.setDate(3, (Date) e.getDataEvento());
            ps.setTime(4, (Time) e.getOrario());
            ps.setDouble(5,e.getMedia());
            ps.setInt(6, s.getIdStruttura());
            ps.setInt(7,e.getIdEvento());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            return true;
        } catch(SQLException ex){
            return false;
        }
    }

    /**
     * Recupera tutti gli eventi dalla base di dati
     * @return la lista di eventi
     */
    public ArrayList<Evento> doRetrieveAll(){
        ArrayList<Evento> result=new ArrayList<Evento>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM evento as eve");
            ResultSet rs = ps.executeQuery();
            EventoExtractor eveExt = new EventoExtractor();
            while(rs.next()) {
                result.add(eveExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera un evento a partire da un identificativo
     * @param id l'identificativo da considerare
     * @return l'evento
     */
    public Evento doRetrieveById(int id){
        Evento e = new Evento();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM evento as eve WHERE idEvento=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            EventoExtractor eveExt= new EventoExtractor();
            if (rs.next())
                e=eveExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return e;
    }

    /**
     * Recupera tutti gli eventi ospitati dalla struttura desiderata
     * @param id l'identificativo della struttura
     * @return gli eventi ospitati dalla struttura
     */
    public ArrayList<Evento> doRetrieveByIdStruttura(int id){
        ArrayList<Evento> result = new ArrayList<Evento>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM evento as eve WHERE eve.str_fk=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                EventoExtractor eveExtractor = new EventoExtractor();
                result.add(eveExtractor.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Elimina un evento dalla base di dati
     * @param id l'identificativo dell'evento da eliminare
     */
    public void deleteById(int id){
        try (Connection con = ConPool.getConnection()) {
            String query ="DELETE FROM evento as eve WHERE eve.idEvento = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
