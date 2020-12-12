package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Rating implements Serializable {
    private int idRating;
    private int Rating;
    private int Beni_idBeni;
    private int Compratore_idCompratore;

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        this.Rating = rating;
    }

    public int getBeni_idBeni() {
        return Beni_idBeni;
    }

    public void setBeni_idBeni(int beni_idBeni) {
        this.Beni_idBeni = beni_idBeni;
    }

    public int getCompratore_idCompratore() {
        return Compratore_idCompratore;
    }

    public void setCompratore_idCompratore(int compratore_idCompratore) {
        this.Compratore_idCompratore = compratore_idCompratore;
    }
}
