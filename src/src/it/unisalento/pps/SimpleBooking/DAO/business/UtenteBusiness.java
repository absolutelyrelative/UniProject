package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.CompratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.VenditoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.util.*;

import javax.mail.Session;
import java.util.ArrayList;

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
    //Uses HASHed password
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

    public void logout() {
        SessionHelper.getInstance().closeSession();
    }

    //Sembra funzionare. Controlla HASH match.
    //Usernames matching -> Error
    //Emails matching -> No error. Should this be fixed?
    //Uses HASHed password
    public Result register(String username, String email) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username);

        //Phase 1 - Check if email is *actually* an email
        EmailValidator e = new EmailValidator();
        if (e.validateEmail(email) == true) { //Phase 2 - check if username already exists
            if (u == null || u.getUsername() == null) { //Phase 2 passed, username not taken
                //Generate password and send it via e-mail if user creation is successful
                PasswordGenerator p = new PasswordGenerator();
                String password = p.generatePassword();
                int hash = new HashGenerator().returnHash(password);
                String realpassword = String.valueOf(hash);
                Utente a = new Utente();
                a.setUsername(username);
                a.setPassword(realpassword);
                a.setEmail(email);
                Result insertion = new Result();
                insertion = UtenteDAO.getInstance().create(a);
                if (insertion.isSuccess() == true) { //No error occurred, send e-mail with password.
                    //Create 'Compratore' & 'Venditore' upon registration. TODO: Remove abstraction level between Utente, Venditore, Compratore.
                    Result cv = this.createCompratoreVenditore(username);
                    if (cv.isSuccess() == true) {
                        new MailHelper().send(email, "SimpleBooking: La tua nuova password", "Ciao.<br >La tua nuova password è: " + password); //USER WILL RECEIVE PLAINTEXT PASSWORD
                        r.setSuccess(true);
                        r.setMessage("Utente added succesfully.");
                        return r;
                    } else {
                        r.setSuccess(false);
                        r.setMessage("Errore durante creazione di Compratore & Venditore.");
                        Utente u_td = UtenteDAO.getInstance().findByUsername(username);
                        if (u_td != null) {
                            UtenteDAO.getInstance().delete(u_td);
                        } else {
                            //Unknown error occurred. TODO: Catch it
                        }
                        return r;
                    }

                } else {
                    r.setSuccess(false);
                    r.setMessage("Unknown error occurred upon utente creation.");
                    r.setType(0x01);
                    return r;
                }


            } else { //Phase 2 failed, username taken
                r.setSuccess(false);
                r.setMessage("Username taken.");
                return r;
            }
        } else { //Phase 1 failed - email is not an email.
            r.setSuccess(false);
            r.setMessage("Insert a *real* E-Mail.");
            return r;
        }
    }

    //Tested, works in all cases
    public Result changePassword(String username, String oldpassword, String newpassword) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username);
        int hash_newPw = new HashGenerator().returnHash(newpassword);
        int hash_oldPw = new HashGenerator().returnHash(oldpassword);

        if (u != null) {
            //Phase 1 - check if old password inserted matches with password in db
            if (String.valueOf(hash_oldPw) == u.getPassword() || String.valueOf(hash_oldPw).equals(u.getPassword())) {
                //Phase 2 - now check if old password is the same as new password
                if (String.valueOf(hash_oldPw) == String.valueOf(hash_newPw) || String.valueOf(hash_oldPw).equals(String.valueOf(hash_newPw))) {
                    //The passwords are the same, show error message
                    r.setSuccess(false);
                    r.setMessage("New password cannot be the same as old password.");
                    return r;
                } else {
                    //Success, passwords differ
                    Result operation = UtenteDAO.getInstance().updatePassword(u, String.valueOf(hash_newPw));
                    if (operation.isSuccess() == true) {
                        //No problem in updating password, continue.
                        r.setSuccess(true);
                        r.setMessage("Password updated. Please Log-in with your new credentials.");
                        this.logout();
                        return r;
                    } else {
                        r.setSuccess(false);
                        r.setMessage("Problem updating password query. Check with administrator.");
                        r.setType(0x01);
                        return r;
                    }
                }
            } else {
                r.setSuccess(false);
                r.setMessage("Old password doesn't match. Check your password or request a reset.");
                return r;
            }
        } else {
            r.setSuccess(false);
            r.setMessage("Utente non trovato. Controlla le credenziali.");
            return r;
        }

    }

    //Tested, works
    public Result requestPasswordReset(String username) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username);
        if (u != null) {
            //Generate password and send it via e-mail if user creation is successful
            PasswordGenerator p = new PasswordGenerator();
            String password = p.generatePassword();
            int hash = new HashGenerator().returnHash(password);
            String realpassword = String.valueOf(hash);

            Result insertion = new Result();
            insertion = UtenteDAO.getInstance().updatePassword(u, realpassword);
            if (insertion.isSuccess() == true) { //No error occurred, send e-mail with password.
                new MailHelper().send(u.getEmail(), "SimpleBooking: Richiesta di reset della password", "Ciao.<br >La tua nuova password è: " + password); //USER WILL RECEIVE PLAINTEXT PASSWORD
                r.setSuccess(true);
                r.setMessage("Password reset succesfully.");
                return r;
            } else {
                r.setSuccess(false);
                r.setMessage("Unknown error occurred upon password reset.");
                r.setType(0x01);
                return r;
            }
        } else {
            r.setSuccess(false);
            r.setMessage("Utente non trovato. Controlla le credenziali.");
            return r;
        }

    }

    public Result createCompratoreVenditore(String username) {
        Result r = new Result();
        Utente u = UtenteDAO.getInstance().findByUsername(username);

        if (u != null) {
            Compratore c = new Compratore();
            c.setUtente_idUtente(u.getId());
            c.setIdUtente(u.getId());
            Result c1 = CompratoreDAO.getInstance().create(c);
            if (c1.isSuccess() == true) {
                Venditore v = new Venditore();
                v.setUtente_idUtente(u.getId());
                v.setIdUtente(u.getId());
                Result v1 = VenditoreDAO.getInstance().create(v);
                if (v1.isSuccess() == true) {
                    r.setSuccess(true);
                    r.setMessage("Compratore & Venditore creati.");
                    return r;
                } else {
                    r.setSuccess(false);
                    r.setMessage("Errore durante creazione di Venditore.");
                    return r;
                }
            } else {
                r.setSuccess(false);
                r.setMessage("Errore durante creazione di Compratore.");
                return r;
            }
        } else {
            r.setSuccess(false);
            r.setMessage("Utente non trovato. Controlla le credenziali.");
            return r;
        }

    }

}
