/**
 * Questa classe modella un Evento. Un evento è ospitato in una struttura, ha utenti che vi partecipano e può essere
 * creato da un utente
 */
package model.evento;

import model.struttura.Struttura;
import model.utente.Utente;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Evento {


    public Evento(){

    }

    /**
     * Crea un nuovo Evento settando gli opportuni parametri
     * @param idEvento l'identificativo dell'evento
     * @param nome il nome dell'evento
     * @param numeroPartecipanti il numero di partecipanti
     * @param data la data dell'evento
     * @param orario l'orario dell'evento
     * @param struttura la Struttura che ospita l'evento
     * @param listaUtenti la lista degli utenti che partecipano all'evento
     */
    public Evento(int idEvento, String nome, int numeroPartecipanti, Date data, Time orario, Struttura struttura, ArrayList<Utente> listaUtenti) {
        this.idEvento = idEvento;
        this.nome = nome;
        this.numeroPartecipanti = numeroPartecipanti;
        this.dataEvento = data;
        this.orario = orario;
        this.struttura = struttura;
        this.listaUtenti = listaUtenti;
    }

    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getNumeroPartecipanti() {
        return numeroPartecipanti;
    }
    public void setNumeroPartecipanti(int numeroPartecipanti) {
        this.numeroPartecipanti = numeroPartecipanti;
    }
    public Date getDataEvento() { return dataEvento; }
    public void setDataEvento(Date data) {
        this.dataEvento = data;
    }
    public Time getOrario() {
        return orario;
    }
    public void setOrario(Time orario) {
        this.orario = orario;
    }
    public Struttura getStruttura() {
        return struttura;
    }
    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }
    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }
    public void setListaUtenti(ArrayList<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }
    public Double getMedia() { return media; }
    public void setMedia(Double media) { this.media = media; }

    @Override
    public String toString() {
        return "model.evento.Evento.Evento{" +
                "idEvento=" + idEvento +
                ", nome='" + nome + '\'' +
                ", numeroPartecipanti=" + numeroPartecipanti +
                ", data=" + dataEvento +
                ", orario=" + orario +
                ", struttura=" + struttura +
                ", listaUtenti=" + listaUtenti +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return idEvento == evento.idEvento && numeroPartecipanti == evento.numeroPartecipanti && Objects.equals(nome, evento.nome) && Objects.equals(dataEvento, evento.dataEvento) && Objects.equals(orario, evento.orario) && Objects.equals(media, evento.media) && Objects.equals(struttura, evento.struttura) && Objects.equals(listaUtenti, evento.listaUtenti);
    }


    private int idEvento;
    private String nome;
    private int numeroPartecipanti;
    private Date dataEvento;
    private Time orario;
    private Double media;
    private Struttura struttura;
    private ArrayList<Utente> listaUtenti;


}
