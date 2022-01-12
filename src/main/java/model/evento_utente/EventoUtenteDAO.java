/**
 * Questa classe modella le interazioni tra la classe EventoUtente e la base di dati
 */

package model.evento_utente;

import model.evento.Evento;
import model.utente.Utente;
import model.utils.ConPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class EventoUtenteDAO {

    /**
     * Inserisce la partecipazione all'interno della base di dati
     * @param e l'evento a cui l'utente partecipa
     * @param u l'utente che partecipa all'evento
     */
    public void addEventoUtente(Evento e, Utente u){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO evento_utente (eve_fk,ute_fk) VALUES(?,?)");
            ps.setInt(1, e.getIdEvento());
            ps.setInt(2, u.getIdUtente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera tutti gli eventi a cui partecipa l'utente scelto
     * @param id l'identificativo dell'utente
     * @return la lista di eventi a cui partecipa l'utente
     */
    public ArrayList<EventoUtente> doRetrieveEventiWithIdUtente(int id){
        ArrayList<EventoUtente> result = new ArrayList<EventoUtente>();
        try (Connection con = ConPool.getConnection()) {
            String query = "SELECT * FROM evento_utente as eu WHERE eu.ute_fk = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            EventoUtenteExtractor euExt = new EventoUtenteExtractor();
            while( rs.next()) {
                EventoUtente eu;
                eu = euExt.extract(rs);
                result.add(eu);
            }
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    /**
     * Recupera tutti gli utenti che partecipano all'evento scelto
     * @param id l'identificativo dell'evento
     * @return la lista di utenti che partecipano all'evento
     */
    public ArrayList<EventoUtente> doRetrieveUtentiWithIdEvento(int id){
        ArrayList<EventoUtente> res=new ArrayList<EventoUtente>();
        try (Connection con = ConPool.getConnection()) {
            String query = "SELECT * FROM evento_utente as eu WHERE eu.eve_fk = (?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            EventoUtenteExtractor euExt = new EventoUtenteExtractor();
            while( rs.next()) {
                EventoUtente eu;
                eu = euExt.extract(rs);
                res.add(eu);
            }
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }
        return res;
    }




}
