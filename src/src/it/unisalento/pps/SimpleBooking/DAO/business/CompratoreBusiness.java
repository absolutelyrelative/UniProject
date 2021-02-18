package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.CompratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Compratore;

public class CompratoreBusiness {
    private static CompratoreBusiness instance;

    public static synchronized CompratoreBusiness getInstance() {
        if (instance == null)
            instance = new CompratoreBusiness();
        return instance;
    }

    private CompratoreBusiness() {
    }
    //Compratore c = CompratoreDAO.getInstance().findById(o.getCompratore_idCompratore());
    public Compratore findById(int id){
        return CompratoreDAO.getInstance().findById(id);
    }
}
