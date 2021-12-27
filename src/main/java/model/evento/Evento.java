package model.evento;

import model.struttura.Struttura;
import model.utente.Utente;

import java.util.ArrayList;
import java.util.Date;

public class Evento {

    public Evento(){ }
    public Evento(int idEvento, String nome, int numeroPartecipanti, Date data, Date orario, Struttura struttura, ArrayList<Utente> listaUtenti) {
        this.idEvento = idEvento;
        this.nome = nome;
        this.numeroPartecipanti = numeroPartecipanti;
        this.data = data;
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
    public Date getData() { return data; }
    public void setData(Date data) {
        this.data = data;
    }
    public Date getOrario() {
        return orario;
    }
    public void setOrario(Date orario) {
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

    @Override
    public String toString() {
        return "model.Evento.Evento{" +
                "idEvento=" + idEvento +
                ", nome='" + nome + '\'' +
                ", numeroPartecipanti=" + numeroPartecipanti +
                ", data=" + data +
                ", orario=" + orario +
                ", struttura=" + struttura +
                ", listaUtenti=" + listaUtenti +
                '}';
    }

    private int idEvento;
    private String nome;
    private int numeroPartecipanti;
    private Date data;
    private Date orario;
    private Struttura struttura;
    private ArrayList<Utente> listaUtenti;


}
