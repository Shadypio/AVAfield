package model.struttura;

public class Struttura {
    public Struttura(){ }
    public Struttura(String nome, String indirizzo, String descrizione, String categoria, int numeroSpogliatoi, String numeroTelefono, int capienza, boolean parcheggio, int idStruttura) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.numeroSpogliatoi = numeroSpogliatoi;
        this.telefono = numeroTelefono;
        this.capienza = capienza;
        this.parcheggio = parcheggio;
        this.idStruttura = idStruttura;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getNumeroSpogliatoi() {
        return numeroSpogliatoi;
    }
    public void setNumeroSpogliatoi(int numeroSpogliatoi) {
        this.numeroSpogliatoi = numeroSpogliatoi;
    }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getCapienza() {
        return capienza;
    }
    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
    public boolean isParcheggio() {
        return parcheggio;
    }

    public void setParcheggio(boolean parcheggio) {
        this.parcheggio = parcheggio;
    }
    public int getIdStruttura() {
        return idStruttura;
    }
    public void setIdStruttura(int idStruttura) {
        this.idStruttura = idStruttura;
    }

    @Override
    public String toString() {
        return "Struttura{" +
                "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", categoria='" + categoria + '\'' +
                ", numeroSpogliatoi=" + numeroSpogliatoi +
                ", telefono=" + telefono +
                ", capienza=" + capienza +
                ", parcheggio=" + parcheggio +
                ", idStruttura=" + idStruttura +
                '}';
    }

    private String nome;
    private String indirizzo;
    private String descrizione;
    private String categoria;
    private int numeroSpogliatoi;
    private String telefono;
    private int capienza;
    private boolean parcheggio;
    private int idStruttura;

}
