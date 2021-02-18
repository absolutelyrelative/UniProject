package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.*;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.util.Result;

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

    public ArrayList<Beni> findOrderedBeni(int compratore_id) {
        ArrayList<Ordine> ordini = OrdineDAO.getInstance().getOrdiniFromCompratore(compratore_id);
        ArrayList<Beni> filtered = new ArrayList<>();

        for (Ordine o : ordini) {
            Beni b = BeniDAO.getInstance().findById(o.getBeni_idBeni());
            filtered.add(b);
        }
        return filtered;
    }

    //c = BeniDAO.getInstance().create(b);
    public Result createBene(Beni b){
        return BeniDAO.getInstance().create(b);
    }

    //BeniDAO.getInstance().updateBene(b, b_new);
    public void updateBene(Beni b, Beni b_new){
        BeniDAO.getInstance().updateBene(b, b_new);
    }

}
