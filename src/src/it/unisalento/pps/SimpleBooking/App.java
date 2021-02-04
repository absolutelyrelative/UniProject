package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Session;

public class App {
    public static void main(String[] args) {
        //new View();
        /*Utente u = UtenteDAO.getInstance().findById(2);
        Session.getInstance().saveSession(u);
        System.out.println("isActive: " + Session.getInstance().isActive());
        System.out.println("email: "+Session.getInstance().getUser().getEmail());
        System.out.println("id: "+Session.getInstance().getUser().getId());*/
        //Session.getInstance().closeSession();
        System.out.println("isActive: " + Session.getInstance().isActive());
    }
}
