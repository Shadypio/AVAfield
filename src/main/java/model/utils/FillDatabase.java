package model.utils;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class FillDatabase {

    private static Random random = new Random();
    private static int size=2000;
    private static int sizeStrutture=10;

    public static void main(String[] args) {
        generateRiserva1();
        generateRiserva2();
        generateRiserva3();
        generateAdmin();
        generateUsers();
        generateStructures();
        generateEvents();
        generateReviews();
        generateUsersEvents();
    }
    private static void generateRiserva1() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into avafieldbase.utente (idUtente, nome, cognome, email, username, password, isAdmin, telefono, autovalutazione) values (?,?,?,?,?,SHA1(?),?,?,?);");
            ps.setInt(1, size+1);
            ps.setString(2, "Alex");
            ps.setString(3, "Rusciano");
            ps.setString(4, "alex.sa4ever@hotmail.it");
            ps.setString(5, "arusc");
            ps.setString(6, "arusc");
            ps.setBoolean(7, false);
            ps.setString(8, "3334445556");
            ps.setInt(9, 3);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateRiserva2() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into avafieldbase.utente (idUtente, nome, cognome, email, username, password, isAdmin, telefono, autovalutazione) values (?,?,?,?,?,SHA1(?),?,?,?);");
            ps.setInt(1, size+2);
            ps.setString(2, "Alessio");
            ps.setString(3, "Alfieri");
            ps.setString(4, "alfio00@hotmail.it");
            ps.setString(5, "alfio");
            ps.setString(6, "alfio");
            ps.setBoolean(7, false);
            ps.setString(8, "3334445556");
            ps.setInt(9, 3);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateRiserva3() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into avafieldbase.utente (idUtente, nome, cognome, email, username, password, isAdmin, telefono, autovalutazione) values (?,?,?,?,?,SHA1(?),?,?,?);");
            ps.setInt(1, size+3);
            ps.setString(2, "Vincenzopio");
            ps.setString(3, "Amendola");
            ps.setString(4, "vinci@hotmail.it");
            ps.setString(5, "shady");
            ps.setString(6, "shady");
            ps.setBoolean(7, false);
            ps.setString(8, "3334445556");
            ps.setInt(9, 3);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
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
            for (int i = 2; i < size; i++) {
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
            for (int i = 1; i < sizeStrutture; i++) {
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
            for (int i = 1; i < size; i++) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO evento (idEvento, nome, numeroPartecipanti, dataEvento, orario, media, str_fk) VALUES(?,?,?,?,?,?,?)");
                ps.setInt(1, i);
                ps.setString(2, "evento" + i);
                ps.setInt(3, random.nextInt(50)+15);
                ps.setDate(4, Date.valueOf("2022-2-11"));
                ps.setTime(5, Time.valueOf("13:20:00"));
                ps.setFloat(6,0);
                ps.setInt(7, random.nextInt(9) + 1);
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
                ps.setInt(5, random.nextInt(size-1) + 1);
                ps.setInt(6, random.nextInt(9) + 1);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateUsersEvents() {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Integer> quanti=new ArrayList<>();
            quanti.add(5);
            quanti.add(7);
            quanti.add(10);
            int countUser=0;
            for (int i = 1; i < size; i++) {
                int partecipanti=random.nextInt(3);
                if ((countUser+partecipanti)>size-1)
                    countUser=0;
                for (int j=0; j<quanti.get(partecipanti); j++) {
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO evento_utente (eve_fk,ute_fk) VALUES(?,?)");
                    ps.setInt(1, i);
                    ps.setInt(2, countUser++);
                    if (ps.executeUpdate() != 1) {
                        throw new RuntimeException("INSERT error.");
                    }
                }
                countUser+=(partecipanti/2);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
