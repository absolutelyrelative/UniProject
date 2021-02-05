package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.HashGenerator;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.mail.Session;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public static synchronized UtenteBusiness getInstance() {
        if(instance == null)
            instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness(){}

    //TODO: TEST!!
    public Result login(String username, String password){
        Result r = new Result();
        int hash = new HashGenerator().returnHash(password);
        String realpassword = String.valueOf(hash);
        //Phase 1 - Data Coherency Check
        if(username == null || username.isEmpty() || realpassword == null || realpassword.isEmpty()){
            r.setSuccess(false);
            r.setMessage("Controlla di aver immesso le credenziali.");
        }
        else{   //Phase 2 - Data Match Check
            boolean match = UtenteDAO.getInstance().validateLogin(username, realpassword);
            if(match == false){
                r.setSuccess(false);
                r.setMessage("Credenziali non valide.");
                return r;
            }
            else{
                Utente u = UtenteDAO.getInstance().findByUsername(username);
                SessionHelper.getInstance().closeSession(); //TODO: CHECK IF THIS WORKS WITH PREVIOUS SESSIONS NOT EXISTING
                SessionHelper.getInstance().saveSession(u);
                r.setSuccess(true);
                r.setMessage("Log-in effettuato correttamente.");
                return r;
            }
        }
        r.setType(0x01); //ERROR OCCURRED
        r.setMessage("Unknown Error Occurred.");
        r.setSuccess(false);
        return r;
    }

}
