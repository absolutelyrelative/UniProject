package it.unisalento.pps.SimpleBooking.Testing;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.AmministratoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.
        Amministratore a = AmministratoreDAO.getInstance().findById(1);
        System.out.println("IdUtente: " + a.getId());
        System.out.println("\neMail: " + a.getEmail());
        System.out.println("\nPassword: " + a.getPassword());
        System.out.println("\nUsername: " + a.getUsername());
        System.out.println("\nIdAmministratore: " + a.getIdAmministratore());
        System.out.println("\nUtente_idUtente: " + a.getUtente_idUtente());
    }
}
