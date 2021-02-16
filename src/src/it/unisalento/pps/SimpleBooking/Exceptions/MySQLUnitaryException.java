package it.unisalento.pps.SimpleBooking.Exceptions;

@Deprecated
public class MySQLUnitaryException extends Exception {
    private static String msg = "[Unitary Record Expected. Received more than one.]";

    public MySQLUnitaryException() {
        super(msg);
        System.out.println(msg);
    }
}
