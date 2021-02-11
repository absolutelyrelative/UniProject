package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;

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
}
