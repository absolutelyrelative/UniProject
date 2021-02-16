package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;

import java.util.ArrayList;

public class VenditoreBusiness {
    private static VenditoreBusiness instance;

    public static synchronized VenditoreBusiness getInstance() {
        if (instance == null)
            instance = new VenditoreBusiness();
        return instance;
    }

    private VenditoreBusiness() {
    }

    public ArrayList<Beni> findOwnBeni(String username) {
        ArrayList<Beni> beni = new ArrayList<>();
        Venditore v = UtenteDAO.getInstance().findIfUserIsVenditore(username);

        if (v != null) {
            beni = BeniDAO.getInstance().sortByCreator(v);
            return beni;
        } else {   //Redundant, but more easily readable this way.
            return beni;
        }

    }
}
