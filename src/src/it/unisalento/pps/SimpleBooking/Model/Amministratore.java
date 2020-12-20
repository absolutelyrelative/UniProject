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

    /*
    private int idUtente;
    private String email;
    private String password;
    private String username;

    public int getId() {}

    public String getEmail() {}

    public String getPassword() {}

    public String getUsername() {}

    public void setIdUtente(int Id) {}

    public void setEmail(String Email) {}

    public void setPassword(String Password) {}

    public void setUsername(String Username) {}
     */
}
