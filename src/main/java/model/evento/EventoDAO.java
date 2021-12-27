package model.evento;

import model.utils.ConPool;
import model.struttura.Struttura;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class EventoDAO {

    public void addEvento(Evento e, Struttura s){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) VALUES(?,?,?,?,?,?)");
            ps.setInt(1,e.getIdEvento());
            ps.setString(2,e.getNome());
            ps.setInt(3,e.getNumeroPartecipanti());
            ps.setDate(4, (Date) e.getDataEvento());
            ps.setDate(5, (Date) e.getOrario());
            ps.setInt(6, s.getIdStruttura());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean doChanges(Evento e, Struttura s){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE evento e SET e.nome = (?), e.numeroPartecipanti = (?), e.data =(?), e.orario=(?), e.struttura=(?) WHERE e.idEvento = (?);");
            ps.setString(1,e.getNome());
            ps.setInt(2,e.getNumeroPartecipanti());
            ps.setDate(3, (Date) e.getDataEvento());
            ps.setDate(4, (Date) e.getOrario());
            ps.setInt(5, s.getIdStruttura());
            ps.setInt(6,e.getIdEvento());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            return true;
        } catch(SQLException ex){
            return false;
        }
    }

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


}
