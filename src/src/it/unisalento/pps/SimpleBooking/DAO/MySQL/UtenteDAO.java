package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IUtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO {
    private static UtenteDAO instance;

    public static UtenteDAO getInstance() {
        if (instance == null)
            instance = new UtenteDAO();
        return instance;
    }
    
}
