package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.ImmagineDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;

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

    public ArrayList<Immagine> getImmaginiFromBene(Beni b){
        int beni_id = b.getIdBeni();

        //TODO: Optimise with a SQL query
        ArrayList<Immagine> all = ImmagineDAO.getInstance().findAll();
        ArrayList<Immagine> sorted = new ArrayList<Immagine>();

        for(Immagine i : all){
            if(i.getBeni_idBeni() == beni_id){
                sorted.add(i);
            }
        }

        return sorted;

    }
}
