package model.utils;

import java.sql.*;
import java.util.Random;

public class FillDatabase {

    private static Random random = new Random();

    public static void main(String[] args) {

        generateAdmin();
        generateUsers();
        generateStructures();
        generateEvents();
        generateReviews();
        generateUsersEvents();


    }

    private static void generateAdmin() {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into avafieldbase.utente (idUtente, nome, cognome, email, username, password, isAdmin, telefono, autovalutazione) values (?,?,?,?,?,SHA1(?),?,?,?);");
            ps.setInt(1, 1);
            ps.setString(2, "Mina");
            ps.setString(3, "Admin");
            ps.setString(4, "minaadmin@gmail.com");
            ps.setString(5, "MinaAdmin");
            ps.setString(6, "admin");
            ps.setBoolean(7, true);
            ps.setString(8, "3334445556");
            ps.setInt(9, 1);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateUsers() {

        try (Connection con = ConPool.getConnection()) {

            for (int i = 2; i < 102; i++) {
                PreparedStatement ps = con.prepareStatement("insert into avafieldbase.utente (idUtente, nome, cognome, email, username, password, isAdmin, telefono, autovalutazione) values (?,?,?,?,?,SHA1(?),?,?,?);");
                ps.setInt(1, i);
                ps.setString(2, "nome" + i);
                ps.setString(3, "cognome" + i);
                ps.setString(4, "utente" + i + "@gmail.com");
                ps.setString(5, "utente" + i);
                ps.setString(6, "password" + i);
                ps.setBoolean(7, false);
                ps.setString(8, "3334445556");
                ps.setInt(9, random.nextInt(5) + 1);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateStructures() {
        String[] categories = {"Pallavolo", "Calcio", "Basket", "Tennis", "Paddle"};
        try (Connection con = ConPool.getConnection()) {
            for (int i = 1; i < 21; i++) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO struttura (idStruttura, nome, indirizzo, telefono, descrizione, capienza, categoria, numeroSpogliatoi, parcheggio) VALUES(?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, i);
                ps.setString(2, "struttura" + i);
                ps.setString(3, "indirizzo" + i);
                ps.setString(4, "3333333333");
                ps.setString(5, "descrizionestruttura" + i);
                ps.setInt(6, random.nextInt(200));
                ps.setString(7, categories[random.nextInt(5)]);
                ps.setInt(8, random.nextInt(10));
                ps.setBoolean(9, random.nextBoolean());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateEvents() {

        try (Connection con = ConPool.getConnection()) {
            for (int i = 1; i < 100; i++) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, str_fk) VALUES(?,?,?,?,?,?)");
                ps.setInt(1, i);
                ps.setString(2, "evento" + i);
                ps.setInt(3, random.nextInt(50)+15);
                ps.setDate(4, Date.valueOf("2022-"+((random.nextInt(12) + 1))+"-"+((random.nextInt(28)+1))));
                //ps.setDate(4, Date.valueOf("2022-2-11"));
                ps.setTime(5, Time.valueOf(random.nextInt(13)+7+":00:00"));
                ps.setInt(6, random.nextInt(20) + 1);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateReviews() {
        try (Connection con = ConPool.getConnection()) {
            for (int i = 1; i < 10; i++) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO recensione (idRecensione, titolo, testo, numeroStelle, ute_fk, str_fk) VALUES(?,?,?,?,?,?)");
                ps.setInt(1, i);
                ps.setString(2, "titolo" + i);
                ps.setString(3, "testo" + i);
                ps.setInt(4, random.nextInt(4) + 1);
                ps.setInt(5, random.nextInt(80) + 1);
                ps.setInt(6, random.nextInt(20) + 1);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateUsersEvents() {

    }
}
