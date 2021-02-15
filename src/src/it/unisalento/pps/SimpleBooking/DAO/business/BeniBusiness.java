package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.VenditoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;

import java.util.ArrayList;

public class BeniBusiness {
    private static BeniBusiness instance;

    public static synchronized BeniBusiness getInstance() {
        if (instance == null)
            instance = new BeniBusiness();
        return instance;
    }

    private BeniBusiness() {
    }

    public Beni getBeneFromName(String name) {
        Beni b = new Beni();
        ArrayList<Beni> all = BeniDAO.getInstance().findAll();
        //Inefficiente, ma più pulito
        for (Beni c : all) {
            if (c.getNome().equals(name)) {
                b = c;
                return b;
            }
        }
        return null;
    }

    public ArrayList<Beni> findAllPublished() {
        ArrayList<Beni> result = new ArrayList<>();
        ArrayList<Beni> all = BeniDAO.getInstance().findAll();
        //Inefficiente, ma più pulito
        for (Beni c : all) {
            if (c.getStato_Bene() == 1 && c.getPubblicazione() == 1) { //Il check su stato bene è ridondante ma non fa male a nessuno
                result.add(c);
            }
        }
        return result;
    }

    public Utente getOwnerofBeni(int beni_id) {
        Beni b = BeniDAO.getInstance().findById(beni_id);
        Venditore v = VenditoreDAO.getInstance().findById(b.getVenditore_idVenditore());
        Utente u = UtenteDAO.getInstance().findById(v.getUtente_idUtente());

        return u;

    }

}
