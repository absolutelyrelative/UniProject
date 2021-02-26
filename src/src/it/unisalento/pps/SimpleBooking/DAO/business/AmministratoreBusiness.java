package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.AmministratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class AmministratoreBusiness {
    private static AmministratoreBusiness instance;

    public static synchronized AmministratoreBusiness getInstance() {
        if (instance == null)
            instance = new AmministratoreBusiness();
        return instance;
    }

    //Gli amministratori possono creare altri amministratori
    public Result createAmministratore(String username) {
        Result result = new Result();
        Utente utente = UtenteDAO.getInstance().findByUsername(username);

        if (utente == null) {
            result.setSuccess(false);
            result.setMessage("Couldn't find user.");
        } else {
            Amministratore amministratore = new Amministratore();
            amministratore.setUtente_idUtente(utente.getId());
            Result operation = AmministratoreDAO.getInstance().create(amministratore);
            if (operation.isSuccess()) {
                result.setSuccess(true);
                result.setMessage("Administrator created. The new administrator has to Log-in again for it to take effect.");
            } else {
                result.setSuccess(false);
                result.setMessage("Something went wrong upon Administrator creation.");
            }
        }

        return result;
    }

    public Result deleteAmministratore(String username) {
        Result result = new Result();
        Utente utente = UtenteDAO.getInstance().findByUsername(username); //This is probably redundant

        if (utente == null) {
            result.setSuccess(false);
            result.setMessage("Couldn't find user.");
        } else {
            Amministratore userIsAdmin = UtenteDAO.getInstance().findIfUserIsAdmin(username);
            if (userIsAdmin == null) {
                result.setSuccess(false);
                result.setMessage("Couldn't find amministratore.");
            } else {
                Result operation = AmministratoreDAO.getInstance().delete(userIsAdmin);
                if (operation.isSuccess()) {
                    result.setSuccess(true);
                    result.setMessage("Administrator successfully deleted.");
                } else {
                    result.setSuccess(false);
                    result.setMessage("Couldn't delete Administrator.");
                }
            }
        }

        return result;
    }

    public ArrayList<Beni> beniToApprove() {
        ArrayList<Beni> beni = new ArrayList<>();
        ArrayList<Beni> all = BeniDAO.getInstance().findAll();

        //Inefficiente ma pulito
        for (Beni bene : all) {
            if (bene.getStato_Bene() == 0) { //0 - Not Approved, 1 - Approved
                beni.add(bene);
            }
        }

        return beni;

    }


    //Amministratore a = UtenteDAO.getInstance().findIfUserIsAdmin(username.getText());

    public Amministratore findIfUserIsAdmin(String username){
        return UtenteDAO.getInstance().findIfUserIsAdmin(username);
    }
}
