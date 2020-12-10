package it.unisalento.pps.SimpleBooking.Model;
import java.io.Serializable;

public class Utente implements Serializable{
    private int id;
    private String email;
    private String password;
    private String username;

    public int getId(){ return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }

    public void setId(int Id) { this.id = Id; }
    public void setEmail(String Email) { this.email = Email;}
    public void setPassword(String Password) { this.password = Password;}
    public void setUsername(String Username) { this.username = Username;}
}
