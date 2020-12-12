package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Ordine implements Serializable {
    private int idOrdine;
    private int Compratore_idCompratore;
    private float Importo_Tot;

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
