package it.unisalento.pps.SimpleBooking.Model;

import java.io.Serializable;
import java.util.Date;

public class Line_Item implements Serializable {
    private int idLine;
    private int Ordine_idOrdine;
    private int Beni_idBeni;
    private Date Data_sel_Inizio;
    private Date Data_sel_Fine;

    public int getIdLine() {
        return idLine;
    }

    public void setIdLine(int idLine) {
        this.idLine = idLine;
    }

    public int getOrdine_idOrdine() {
        return Ordine_idOrdine;
    }

    public void setOrdine_idOrdine(int ordine_idOrdine) {
        Ordine_idOrdine = ordine_idOrdine;
    }

    public int getBeni_idBeni() {
        return Beni_idBeni;
    }

    public void setBeni_idBeni(int beni_idBeni) {
        this.Beni_idBeni = beni_idBeni;
    }

    public Date getData_sel_Inizio() {
        return Data_sel_Inizio;
    }

    public void setData_sel_Inizio(Date data_sel_Inizio) {
        this.Data_sel_Inizio = data_sel_Inizio;
    }

    public Date getData_sel_Fine() {
        return Data_sel_Fine;
    }

    public void setData_sel_Fine(Date data_sel_Fine) {
        this.Data_sel_Fine = data_sel_Fine;
    }
}
