package model.utente;


import model.evento.Evento;

import java.util.ArrayList;

public class Utente {
    public Utente(){ }
    public Utente(String nome, String cognome, String email, String username, String password, String numeroTelefono, boolean isAdmin, int autovalutazione, int idUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.numeroTelefono = numeroTelefono;
        this.isAdmin = isAdmin;
        this.autovalutazione = autovalutazione;
        this.idUtente = idUtente;
    }
    public Utente(String nome, String cognome, String email, String username, String password, String numeroTelefono, boolean isAdmin, int autovalutazione, int idUtente, ArrayList<Evento> listaEventi) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.numeroTelefono = numeroTelefono;
        this.isAdmin = isAdmin;
        this.autovalutazione = autovalutazione;
        this.idUtente = idUtente;
        this.listaEventi = listaEventi;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public int getAutovalutazione() {
        return autovalutazione;
    }
    public void setAutovalutazione(int autovalutazione) {
        this.autovalutazione = autovalutazione;
    }
    public int getIdUtente() {
        return idUtente;
    }
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    public ArrayList<Evento> getListaEventi() {
        return listaEventi;
    }
    public void setListaEventi(ArrayList<Evento> listaEventi) {
        this.listaEventi = listaEventi;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", isAdmin=" + isAdmin +
                ", autovalutazione=" + autovalutazione +
                ", idUtente=" + idUtente +
                ", listaEventi=" + listaEventi +
                '}';
    }

    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    private String numeroTelefono;
    private boolean isAdmin;
    private int autovalutazione;
    private int idUtente;
    private ArrayList<Evento> listaEventi;
}
