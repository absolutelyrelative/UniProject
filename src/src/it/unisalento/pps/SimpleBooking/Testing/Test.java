package it.unisalento.pps.SimpleBooking.Testing;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.AmministratoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.
        Amministratore a = AmministratoreDAO.getInstance().findById(1);
        System.out.print(a.getIdAmministratore() + '\n' + a.getId() + '\n' + a.getUtente_idUtente() + '\n' + a.getEmail() + '\n' + a.getPassword() + '\n' + a.getUsername());
    }
}
