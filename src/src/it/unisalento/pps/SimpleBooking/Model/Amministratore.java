package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Amministratore extends Utente implements Serializable {
    private int idAmministratore;
    private int Utente_idUtente;

    public int getIdAmministratore() {
        return idAmministratore;
    }

    public void setIdAmministratore(int idAmministratore) {
        this.idAmministratore = idAmministratore;
    }

    public int getUtente_idUtente() {
        return Utente_idUtente;
    }

    public void setUtente_idUtente(int utente_idUtente) {
        this.Utente_idUtente = utente_idUtente;
    }
}
