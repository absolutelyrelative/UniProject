package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Compratore extends Utente implements Serializable {
    private int idCompratore;
    private int Utente_idUtente;

    public int getIdCompratore() {
        return idCompratore;
    }

    public void setIdCompratore(int idCompratore) {
        this.idCompratore = idCompratore;
    }

    public int getUtente_idUtente() {
        return Utente_idUtente;
    }

    public void setUtente_idUtente(int utente_idUtente) {
        this.Utente_idUtente = utente_idUtente;
    }
}
