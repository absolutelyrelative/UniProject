package it.unisalento.pps.SimpleBooking.util;

public class Result {
    //Scritto preventivamente per far in modo che gli errori di coerenza dati possano essere generati indipendentemente
    //dalla GUI, e mostrati più dinamicamente possibile.
    private String message;
    private int type; //0x00 - Warning, 0x01 - Error (?), TODO: considera cambiare in bool

    public String getMessage() {
        return message;
    }

    public void setMessage(String rs_message) {
        this.message = rs_message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) { //0x00 - Warning, 0x01 - Error (?)
        this.type = type;
    }
}
