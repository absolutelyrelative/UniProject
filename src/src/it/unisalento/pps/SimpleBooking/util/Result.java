package it.unisalento.pps.SimpleBooking.util;

public class Result {
    //Scritto preventivamente per far in modo che gli errori di coerenza dati possano essere generati indipendentemente
    //dalla GUI, e mostrati più dinamicamente possibile.
    private String message;
    private boolean success = false;
    private int type; //0x00 - Warning, 0x01 - Error (?), TODO: considera cambiare in bool

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
