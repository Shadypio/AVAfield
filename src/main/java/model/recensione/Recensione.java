package model.recensione;

import model.struttura.Struttura;
import model.utente.Utente;

public class Recensione {
    public Recensione(){ }
    public Recensione(int idRecensione, String titolo, String testo, int numeroStelle, Utente utente, Struttura struttura) {
        this.idRecensione = idRecensione;
        this.titolo = titolo;
        this.testo = testo;
        this.numeroStelle = numeroStelle;
        this.utente = utente;
        this.struttura = struttura;
    }

    public int getIdRecensione() {
        return idRecensione;
    }
    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getTesto() {
        return testo;
    }
    public void setTesto(String testo) {
        this.testo = testo;
    }
    public int getNumeroStelle() {
        return numeroStelle;
    }
    public void setNumeroStelle(int numeroStelle) {
        this.numeroStelle = numeroStelle;
    }
    public Utente getUtente() {
        return utente;
    }
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    public Struttura getStruttura() {
        return struttura;
    }
    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }

    @Override
    public String toString() {
        return "Recensione{" +
                "idRecensione=" + idRecensione +
                ", titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", numeroStelle=" + numeroStelle +
                ", utente=" + utente +
                ", struttura=" + struttura +
                '}';
    }

    private int idRecensione;
    private String titolo;
    private String testo;
    private int numeroStelle;
    private Utente utente;
    private Struttura struttura;
}
