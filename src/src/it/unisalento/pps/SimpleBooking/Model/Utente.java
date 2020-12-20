package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Utente implements Serializable {
    private int idUtente;
    private String email;
    private String password;
    private String username;

    public int getId() {
        return idUtente;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setIdUtente(int Id) {
        this.idUtente = Id;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }
}
