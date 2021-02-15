package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;
import java.util.Date;

public class Ordine implements Serializable {
    private int idOrdine;
    private int Compratore_idCompratore;
    private float Importo_Tot;
    private Date Data_Inizio;
    private Date Data_Fine;
    private int Beni_idBeni;

    public Date getData_Inizio() {
        return Data_Inizio;
    }

    public void setData_Inizio(Date data_Inizio) {
        Data_Inizio = data_Inizio;
    }

    public Date getData_Fine() {
        return Data_Fine;
    }

    public void setData_Fine(Date data_Fine) {
        Data_Fine = data_Fine;
    }

    public int getBeni_idBeni() {
        return Beni_idBeni;
    }

    public void setBeni_idBeni(int beni_idBeni) {
        Beni_idBeni = beni_idBeni;
    }

    public String getBeni_Nome() {
        return Beni_Nome;
    }

    public void setBeni_Nome(String beni_Nome) {
        Beni_Nome = beni_Nome;
    }

    private String Beni_Nome;

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getCompratore_idCompratore() {
        return Compratore_idCompratore;
    }

    public void setCompratore_idCompratore(int compratore_idCompratore) {
        Compratore_idCompratore = compratore_idCompratore;
    }

    public float getImporto_Tot() {
        return Importo_Tot;
    }

    public void setImporto_Tot(float importo_Tot) {
        this.Importo_Tot = importo_Tot;
    }
}
