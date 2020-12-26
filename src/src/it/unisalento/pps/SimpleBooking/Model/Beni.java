package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;
import java.util.Date;

public class Beni implements Serializable {
    private int idBeni;
    private String Nome;
    private String Descrizione;
    private java.sql.Date Data_Inizio;
    private java.sql.Date Data_Fine;
    private float Costo_pw;
    private float Costo_pm;
    private float Costo_pd;
    private float GPS_Lat;
    private float GPS_Lon;
    private String Addr;
    private int Venditore_idVenditore;
    private int Tipo_Bene_idTipo_Bene;
    private int Stato_Bene;
    private int Pubblicazione;
    private int Amministratore_idAmministratore;

    public int getIdBeni() {
        return idBeni;
    }

    public void setIdBeni(int idBeni) {
        this.idBeni = idBeni;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.Descrizione = descrizione;
    }

    public java.sql.Date getData_Inizio() {
        return Data_Inizio;
    }

    public void setData_Inizio(java.sql.Date data_Inizio) {
        this.Data_Inizio = data_Inizio;
    }

    public java.sql.Date getData_Fine() {
        return Data_Fine;
    }

    public void setData_Fine(java.sql.Date data_Fine) {
        this.Data_Fine = data_Fine;
    }

    public float getCosto_pw() {
        return Costo_pw;
    }

    public void setCosto_pw(float costo_pw) {
        this.Costo_pw = costo_pw;
    }

    public float getCosto_pm() {
        return Costo_pm;
    }

    public void setCosto_pm(float costo_pm) {
        this.Costo_pm = costo_pm;
    }

    public float getCosto_pd() {
        return Costo_pd;
    }

    public void setCosto_pd(float costo_pd) {
        this.Costo_pd = costo_pd;
    }

    public float getGPS_Lat() {
        return GPS_Lat;
    }

    public void setGPS_Lat(float GPS_Lat) {
        this.GPS_Lat = GPS_Lat;
    }

    public float getGPS_Lon() {
        return GPS_Lon;
    }

    public void setGPS_Lon(float GPS_Lon) {
        this.GPS_Lon = GPS_Lon;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        this.Addr = addr;
    }

    public int getVenditore_idVenditore() {
        return Venditore_idVenditore;
    }

    public void setVenditore_idVenditore(int venditore_idVenditore) {
        this.Venditore_idVenditore = venditore_idVenditore;
    }

    public int getTipo_Bene_idTipo_Bene() {
        return Tipo_Bene_idTipo_Bene;
    }

    public void setTipo_Bene_idTipo_Bene(int tipo_Bene_idTipo_Bene) {
        this.Tipo_Bene_idTipo_Bene = tipo_Bene_idTipo_Bene;
    }

    public int getStato_Bene() {
        return Stato_Bene;
    }

    public void setStato_Bene(int stato_Bene) {
        this.Stato_Bene = stato_Bene;
    }

    public int getPubblicazione() {
        return Pubblicazione;
    }

    public void setPubblicazione(int pubblicazione) {
        this.Pubblicazione = pubblicazione;
    }

    public int getAmministratore_idAmministratore() {
        return Amministratore_idAmministratore;
    }

    public void setAmministratore_idAmministratore(int amministratore_idAmministratore) {
        this.Amministratore_idAmministratore = amministratore_idAmministratore;
    }
}
