package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;

public class Pagamento implements Serializable {
    private int idPagamento;
    private int Stato;
    private int Ordine_idOrdine;
    private String Numero_Carta;
    private String CVV;
    private String PIN;
    private float Importo;

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getStato() {
        return Stato;
    }

    public void setStato(int stato) {
        this.Stato = stato;
    }

    public int getOrdine_idOrdine() {
        return Ordine_idOrdine;
    }

    public void setOrdine_idOrdine(int ordine_idOrdine) {
        this.Ordine_idOrdine = ordine_idOrdine;
    }

    public String getNumero_Carta() {
        return Numero_Carta;
    }

    public void setNumero_Carta(String numero_Carta) {
        this.Numero_Carta = numero_Carta;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public float getImporto() {
        return Importo;
    }

    public void setImporto(float importo) {
        this.Importo = importo;
    }
}
