package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.ImmagineDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class ImmagineBusiness {
    private static ImmagineBusiness instance;

    public static synchronized ImmagineBusiness getInstance() {
        if (instance == null)
            instance = new ImmagineBusiness();
        return instance;
    }

    private ImmagineBusiness() {
    }

    public ArrayList<Immagine> getImmaginiFromBene(Beni b) {
        int beni_id = b.getIdBeni();

        //TODO: Optimise with a SQL query
        ArrayList<Immagine> all = ImmagineDAO.getInstance().findAll();
        ArrayList<Immagine> sorted = new ArrayList<Immagine>();

        for (Immagine i : all) {
            if (i.getBeni_idBeni() == beni_id) {
                sorted.add(i);
            }
        }

        return sorted;

    }

    public Result removeImmaginiFromBene(Beni b) {
        Result r = new Result();
        int beni_id = b.getIdBeni();

        //TODO: Optimise with a SQL query
        ArrayList<Immagine> all = ImmagineDAO.getInstance().findAll();
        ArrayList<Immagine> sorted = new ArrayList<Immagine>();
        int result = 1;

        for (Immagine i : all) {
            if (i.getBeni_idBeni() == beni_id) {
                Result c = ImmagineDAO.getInstance().delete(i);
                if (!c.isSuccess()) {
                    result = 0;
                }
            }
        }

        if (result == 1) {
            r.setSuccess(true);
            r.setMessage("All images have been deleted.");
        } else {
            r.setSuccess(false);
            r.setMessage("Not all images have been deleted.");
        }

        return r;
    }
}
