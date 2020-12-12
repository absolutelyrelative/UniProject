package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Feedback implements Serializable {
    private int idFeedback;
    private String Commento;
    private int Feedback_idFeedback;
    private int Beni_idBeni;
    private int Compratore_idCompratore;
    private int Venditore_idVenditore;

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getCommento() {
        return Commento;
    }

    public void setCommento(String commento) {
        this.Commento = commento;
    }

    public int getFeedback_idFeedback() {
        return Feedback_idFeedback;
    }

    public void setFeedback_idFeedback(int feedback_idFeedback) {
        this.Feedback_idFeedback = feedback_idFeedback;
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

    public int getVenditore_idVenditore() {
        return Venditore_idVenditore;
    }

    public void setVenditore_idVenditore(int venditore_idVenditore) {
        this.Venditore_idVenditore = venditore_idVenditore;
    }
}
