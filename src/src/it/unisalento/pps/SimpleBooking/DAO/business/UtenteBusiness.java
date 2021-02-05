package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.*;

import javax.mail.Session;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public static synchronized UtenteBusiness getInstance() {
        if (instance == null)
            instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {
    }


    //[Tested, works in all three cases.]
    public Result login(String username, String password) {
        Result r = new Result();
        int hash = new HashGenerator().returnHash(password);
        String realpassword = String.valueOf(hash);
        //Phase 1 - Data Coherency Check
        if (username == null || username.isEmpty() || realpassword == null || realpassword.isEmpty() || username == "" || password == "") {
            r.setSuccess(false);
            r.setMessage("Controlla di aver immesso le credenziali.");
            return r;
        } else {   //Phase 2 - Data Match Check
            boolean match = UtenteDAO.getInstance().validateLogin(username, realpassword); //TODO: change to 'realpassword' when passwords will be stored as HASH
            if (match == false) {
                r.setSuccess(false);
                r.setMessage("Credenziali non valide.");
                return r;
            } else {
                Utente u = UtenteDAO.getInstance().findByUsername(username);
                SessionHelper.getInstance().closeSession(); //[IT DOES] CHECK IF THIS WORKS WITH PREVIOUS SESSIONS NOT EXISTING.
                SessionHelper.getInstance().saveSession(u);
                r.setSuccess(true);
                r.setMessage("Log-in effettuato correttamente.");
                return r;
            }
        }
    }

    //TODO: TEST
    //TODO: IMPLEMENT PASSWORD HASH
    public Result register(String username, String email) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username);

        //Phase 1 - Check if email is *actually* an email
        EmailValidator e = new EmailValidator();
        if(e.validateEmail(email) == true){ //Phase 2 - check if username already exists
            if(u == null || u.getUsername() == null){ //Phase 2 passed, username not taken
                //Generate password and send it via e-mail if user creation is successful
                PasswordGenerator p = new PasswordGenerator();
                String password = p.generatePassword();
                Utente a = new Utente();
                a.setUsername(username);
                a.setPassword(password);
                a.setEmail(email);
                Result insertion = new Result();
                insertion = UtenteDAO.getInstance().createWithResult(a);
                if(insertion.isSuccess() == true){ //No error occurred, send e-mail with password.
                    new MailHelper().send(email, "SimpleBooking: La tua nuova password", "Ciao.<br >La tua nuova password è: " + password);
                    r.setSuccess(true);
                    r.setMessage("Utente added succesfully.");
                    return r;
                }
                else{
                    r.setSuccess(false);
                    r.setMessage("Unknown error occurred upon utente creation.");
                    r.setType(0x01);
                    return r;
                }


            }
            else{ //Phase 2 failed, username taken
                r.setSuccess(false);
                r.setMessage("Username taken.");
                return r;
            }
        }
        else{ //Phase 1 failed - email is not an email.
            r.setSuccess(false);
            r.setMessage("Insert a *real* E-Mail.");
            return r;
        }
    }

}
