package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Immagine implements Serializable {
    private int idImmagine;
    private Byte[] Data;
    private int Beni_idBeni;

    public int getIdImmagine() {
        return idImmagine;
    }

    public void setIdImmagine(int idImmagine) {
        this.idImmagine = idImmagine;
    }

    public Byte[] getData() {
        return Data;
    }

    public void setData(Byte[] data) {
        this.Data = data; //TODO: COHERENCY CHECK
    }

    public int getBeni_idBeni() {
        return Beni_idBeni;
    }

    public void setBeni_idBeni(int beni_idBeni) {
        this.Beni_idBeni = beni_idBeni;
    }
}
