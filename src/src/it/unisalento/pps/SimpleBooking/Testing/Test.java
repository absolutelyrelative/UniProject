package it.unisalento.pps.SimpleBooking.Testing;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.AmministratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        /*System.out.println("Hello World!"); // Display the string.
        Amministratore a = AmministratoreDAO.getInstance().findById(1);
        System.out.println("IdUtente: " + a.getId());
        System.out.println("\neMail: " + a.getEmail());
        System.out.println("\nPassword: " + a.getPassword());
        System.out.println("\nUsername: " + a.getUsername());
        System.out.println("\nIdAmministratore: " + a.getIdAmministratore());
        System.out.println("\nUtente_idUtente: " + a.getUtente_idUtente());*/


        /*Utente u = new Utente();
        u.setIdUtente(27);
        u.setEmail("TEST@TEST");
        u.setPassword("TESTPW");
        u.setUsername("TESTUSERNAME");
        UtenteDAO.getInstance().create(u);*/

        /*ArrayList<Utente> utenti = UtenteDAO.getInstance().findAll();
        for (Utente u : utenti) {
            System.out.println("\nIdUtente = " + u.getId());
            System.out.println(" email = " + u.getEmail());
            System.out.println(" password = " + u.getPassword());
            System.out.println(" username = " + u.getUsername());
        }

        Utente td = UtenteDAO.getInstance().findById(27);
        UtenteDAO.getInstance().delete(td);

        ArrayList<Utente> utenti2 = UtenteDAO.getInstance().findAll();
        for (Utente u : utenti2) {
            System.out.println("\nIdUtente = " + u.getId());
            System.out.println(" email = " + u.getEmail());
            System.out.println(" password = " + u.getPassword());
            System.out.println(" username = " + u.getUsername());
        }*/
        Utente u = UtenteDAO.getInstance().findById(23);
        UtenteDAO.getInstance().delete(u);
    }
}
