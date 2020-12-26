package it.unisalento.pps.SimpleBooking.Testing;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.AmministratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;

import java.util.ArrayList;
import java.util.Calendar;

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
       /* Utente u = UtenteDAO.getInstance().findById(23);
        UtenteDAO.getInstance().delete(u);
        */

        /*int idBeni = 1;
        String nome = "nom";
        String descrizione = "descr";
        java.sql.Date Data_Inizio = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date Data_Fine = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        float GPS_Lat = 4f;
        float GPS_Lon = 5f;
        String Addr = "Add";
        int Venditore_idVenditore = 2;
        int Tipo_Bene_idTipo_Bene = 3;
        int Stato_Bene = 4;
        int Pubblicazione = 5;
        int Amministratore_idAmministratore = 6;

        String query = "INSERT INTO Beni(idBeni, Nome, Descrizione, Data_Inizio, Data_Fine, Costo_pw, Costo_pm, Costo_pd" +
                ", GPS_Lat, GPS_Lon, Addr, Venditore_idVenditore, Tipo_Bene_idTipo_Bene, Stato_Bene, Pubblicazione, " +
                "Amministratore_idAmministratore) VALUES('" + idBeni + "', '" + nome + "','" + descrizione + "','" + Data_Inizio + "','" + Data_Fine + "','" + GPS_Lat + "','" + GPS_Lon + "','" + Addr + "'," +
                "'" + Venditore_idVenditore + "','" + Tipo_Bene_idTipo_Bene + "','" + Stato_Bene + "','" + Pubblicazione + "','" + Amministratore_idAmministratore + "');";

        System.out.println(query);*/

        long dec = 1606827600000L; //December 01, 2020 13:00
        long sep = 1598965200000L; //September 01, 2020 13:00
        long feb = 1612184400000L;
        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date end = new java.sql.Date(feb);
        java.sql.Date beg = new java.sql.Date(sep);
        java.sql.Date dece = new java.sql.Date(dec);

        System.out.println(now);
        System.out.println("\n");
        System.out.println(beg);
        System.out.println("\n");
        System.out.println(end);

        //It returns the value 0 if the argument Date is equal to this Date.
        //It returns a value less than 0 if this Date is before the Date argument.
        //It returns a value greater than 0 if this Date is after the Date argument.
        System.out.println(now.compareTo(end));
    }
}
