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
        Beni beni = new Beni();
        ArrayList<Beni> all = BeniDAO.getInstance().findAll();
        //Inefficiente, ma più pulito
        for (Beni bene : all) {
            if (bene.getNome().equals(name)) {
                beni = bene;
                return beni;
            }
        }
        return null;
    }

    public ArrayList<Beni> findAllPublished() {
        ArrayList<Beni> result = new ArrayList<>();
        ArrayList<Beni> all = BeniDAO.getInstance().findAll();
        //Inefficiente, ma più pulito
        for (Beni bene : all) {
            if (bene.getStato_Bene() == 1 && bene.getPubblicazione() == 1) { //Il check su stato bene è ridondante ma non fa male a nessuno
                result.add(bene);
            }
        }
        return result;
    }

    public Utente getOwnerofBeni(int beni_id) {
        Beni beni = BeniDAO.getInstance().findById(beni_id);
        Venditore venditore = VenditoreDAO.getInstance().findById(beni.getVenditore_idVenditore());
        Utente utente = UtenteDAO.getInstance().findById(venditore.getUtente_idUtente());

        return utente;

    }

    public ArrayList<Beni> findOrderedBeni(int compratore_id) {
        ArrayList<Ordine> ordini = OrdineDAO.getInstance().getOrdiniFromCompratore(compratore_id);
        ArrayList<Beni> filtered = new ArrayList<>();

        for (Ordine ordine : ordini) {
            Beni beni = BeniDAO.getInstance().findById(ordine.getBeni_idBeni());
            filtered.add(beni);
        }
        return filtered;
    }

    //c = BeniDAO.getInstance().create(b);
    public Result createBene(Beni beni){
        return BeniDAO.getInstance().create(beni);
    }

    //BeniDAO.getInstance().updateBene(b, b_new);
    public void updateBene(Beni beniOld, Beni beniNew){
        BeniDAO.getInstance().updateBene(beniOld, beniNew);
    }

}
