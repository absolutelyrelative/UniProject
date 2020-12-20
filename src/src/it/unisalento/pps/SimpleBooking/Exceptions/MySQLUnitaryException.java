package it.unisalento.pps.SimpleBooking.Exceptions;

public class MySQLUnitaryException extends Exception{
    private static String msg = "[Unitary Record Expected. Received more than one.]";

    public MySQLUnitaryException(){
        System.out.println(super(msg));
    }
}
