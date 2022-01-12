/**
 * Questa classe modella le interazioni tra la classe Utente e la base di dati. Sono previsti i metodi
 * principali delle operazioni CRUD
 */

package model.utente;


import model.utils.ConPool;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {
    /**
     * Inserisce un utente all'interno della base di dati
     * @param u l'utente da inserire
     * @return
     */
    public boolean addUtente(Utente u){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (idUtente, nome, cognome, email, username, password, isAdmin, telefono, autovalutazione) VALUES(?,?,?,?,?,SHA1(?),?,?,?)");
            ps.setInt(1, u.getIdUtente());
            ps.setString(2,u.getNome());
            ps.setString(3,u.getCognome());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.setBoolean(7, u.isAdmin());
            ps.setString(8,u.getNumeroTelefono());
            ps.setInt(9,u.getAutovalutazione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera tutti gli utenti dalla base di dati
     * @return la lista di utenti
     */
    public ArrayList<Utente> doRetrieveAll(){
        ArrayList<Utente> result=new ArrayList<Utente>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente AS ute");
            ResultSet rs = ps.executeQuery();
            UtenteExtractor uteExt = new UtenteExtractor();
            while(rs.next()) {
                result.add(uteExt.extract(rs));
            }
            return result;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recupera un utente a partire da un identificativo
     * @param id l'identificativo da considerare
     * @return l'utente
     */
    public Utente doRetrieveById(int id){
        Utente u = new Utente();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente AS ute WHERE idUtente=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            UtenteExtractor uteExt= new UtenteExtractor();
            if (rs.next())
                u=uteExt.extract(rs);
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return u;
    }

    /**
     * Elimina un utente dalla base di dati
     * @param id l'identificativo dell'utente da eliminare
     */
    public void deleteById(int id){
        try (Connection con = ConPool.getConnection()) {
            String query ="DELETE FROM utente AS ute WHERE ute.idUtente = (?);";
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
     * Apporta delle modifiche all'utente selezionato
     * @param u l'utente da modificare
     */
    public void doChanges(Utente u){
        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "UPDATE utente ute SET ute.nome='" + u.getNome() + "', " + "ute.cognome='"+u.getCognome() + "', ute.email='"+u.getEmail() +"'," +
                    "ute.username='"+u.getUsername()+"', ute.autovalutazione='"+u.getAutovalutazione()+"', ute.isAdmin="+
                    u.isAdmin()+", ute.telefono='"+u.getNumeroTelefono()+"' WHERE ute.idUtente=" + u.getIdUtente() + ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Apporta delle modifiche all'utente selezionato
     * @param u l'utente da modificare
     */
    public void doChangesWithPass(Utente u){
        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "UPDATE utente ute SET ute.nome='" + u.getNome() + "', " + "ute.cognome='"+u.getCognome() + "', ute.email='"+u.getEmail() +"'," +
                    "ute.username='"+u.getUsername()+ "',ute.password='"+u.getPassword()+"', ute.autovalutazione='"+u.getAutovalutazione()+"', ute.isAdmin="+
                    u.isAdmin()+", ute.telefono='"+u.getNumeroTelefono()+"' WHERE ute.idUtente=" + u.getIdUtente() + ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Recupera un utente a partire da email e password
     * @param email l'email dell'utente
     * @param pass la password dell'utente
     * @return l'utente con email e password corretti
     */
    public Utente doRetrieveUtenteByEmailPassword(String email,String pass){
        Utente u = null;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente AS ute WHERE ute.email=? AND ute.password=SHA1(?)");
            ps.setString(1,email);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            UtenteExtractor uteExt=new UtenteExtractor();
            if(rs.next())
                u=uteExt.extract(rs);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

}
