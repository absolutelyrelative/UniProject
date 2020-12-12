package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Venditore extends Utente implements Serializable {
    private int idVenditore;
    private int Utente_idUtente;

    public int getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    public int getUtente_idUtente() {
        return Utente_idUtente;
    }

    public void setUtente_idUtente(int utente_idUtente) {
        this.Utente_idUtente = utente_idUtente;
    }
}
