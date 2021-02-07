package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.AmministratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.Result;

public class AmministratoreBusiness {
    private static AmministratoreBusiness instance;

    public static synchronized AmministratoreBusiness getInstance() {
        if (instance == null)
            instance = new AmministratoreBusiness();
        return instance;
    }

    //Gli amministratori possono creare altri amministratori
    public Result createAmministratore(String username) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username);

        if (u == null) {
            r.setSuccess(false);
            r.setMessage("Couldn't find user.");
        } else {
            Amministratore a = new Amministratore();
            a.setUtente_idUtente(u.getId());
            Result operation = AmministratoreDAO.getInstance().create(a);
            if (operation.isSuccess() == true) {
                r.setSuccess(true);
                r.setMessage("Administrator created. The new administrator has to Log-in again for it to take effect.");
            } else {
                r.setSuccess(false);
                r.setMessage("Something went wrong upon Administrator creation.");
            }
        }

        return r;
    }

    public Result deleteAmministratore(String username) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username); //This is probably redundant

        if (u == null) {
            r.setSuccess(false);
            r.setMessage("Couldn't find user.");
        } else {
            Amministratore a = UtenteDAO.getInstance().findIfUserIsAdmin(username);
            if (a == null) {
                r.setSuccess(false);
                r.setMessage("Couldn't find amministratore.");
            } else {
                Result operation = AmministratoreDAO.getInstance().delete(a);
                if (operation.isSuccess() == true) {
                    r.setSuccess(true);
                    r.setMessage("Administrator successfully deleted.");
                } else {
                    r.setSuccess(false);
                    r.setMessage("Couldn't delete Administrator.");
                }
            }
        }

        return r;
    }

}
