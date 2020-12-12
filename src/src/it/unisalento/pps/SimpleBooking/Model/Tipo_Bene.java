package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Tipo_Bene implements Serializable {
    private int idTipo;
    private String Nome;
    private int Amministratore_idAmministratore;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public int getAmministratore_idAmministratore() {
        return Amministratore_idAmministratore;
    }

    public void setAmministratore_idAmministratore(int amministratore_idAmministratore) {
        this.Amministratore_idAmministratore = amministratore_idAmministratore;
    }
}
