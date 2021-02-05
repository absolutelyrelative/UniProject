package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IUtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

//TODO: CHECK IF String.valueOf(id) IS PROPER USAGE

public class UtenteDAO implements IUtenteDAO {
    private static UtenteDAO instance;

    public static UtenteDAO getInstance() {
        if (instance == null)
            instance = new UtenteDAO();
        return instance;
    }

    //TESTED THROUGH FINDALL() METHOD
    @Override
    public Utente findById(int id) {
        Utente a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idUtente, email, password, username FROM Utente WHERE idUtente = '" + String.valueOf(id) + "' LIMIT 1;");

        try {
            String[] result = res.get(0);
            a = new Utente();
            a.setIdUtente(Integer.parseInt(result[0]));
            a.setEmail(result[1]);
            a.setPassword(result[2]);
            a.setUsername(result[3]);
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }


        return a;
    }

    //TESTED
    @Override
    public ArrayList<Utente> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idUtente FROM Utente;");
        ArrayList<Utente> utenti = new ArrayList<>();

        for (String[] row : res) {
            Utente a = findById(Integer.parseInt(row[0]));
            utenti.add(a);
        }

        return utenti;
    }


    //TODO: TEST WITH NEW DATA COHERENCY CHECK!!
    public Result create(Utente a) {
        Result r = new Result();
        //int idUtente = a.getId();
        String email = a.getEmail();
        String password = a.getPassword();
        String username = a.getUsername();

        //DB COHERENCY CHECK
        if (email.length() > 45 || password.length() > 45 || username.length() > 45) {
            r.setSuccess(false);
            r.setMessage("Data coherency fail. Check email lenght <= 45, password length <= 45, username length < 45.");
            return r;
        } else {
            if (DbConnection.getInstance().eseguiAggiornamento("insert into Utente (email, password, username) values ('" + email + "', '" + password + "', '" + username + "');") == true) {
                r.setSuccess(true);
                r.setMessage("Data inserted in DB.");
                return r;
            } else {
                r.setSuccess(false);
                r.setMessage("DB Error Occurred.");
                return r;
            }

        }
    }


    //Cascade delete tested successful on venditore
    @Override
    public Result delete(Utente a) {
        Result r = new Result();
        int idUtente_td = a.getId();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Utente WHERE idUtente = '" + String.valueOf(idUtente_td) + "';");
        if (operation == true) {
            r.setSuccess(true);
            return r;
        } else {
            r.setSuccess(false);
            return r;
        }
    }

    //[Tested with Log-in routine]
    public Utente findByUsername(String username) {
        Utente a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idUtente, email, password, username FROM Utente WHERE username = '" + username + "' LIMIT 1;");

        try {
            String[] result = res.get(0);
            a = new Utente();
            a.setIdUtente(Integer.parseInt(result[0]));
            a.setEmail(result[1]);
            a.setPassword(result[2]);
            a.setUsername(result[3]);
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }


        return a;
    }

    //[Tested with Log-in routine]
    public Amministratore findIfUserIsAdmin(String username) {
        Utente u;
        u = this.findByUsername(username); //CHECK IF this.findByUsername IS PROPER USAGE
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idAmministratore, Utente_idUtente FROM Amministratore WHERE Utente_idUtente = '" + String.valueOf(u.getId()) + "' LIMIT 1;");
        if (res.size() == 0 || res == null || res.isEmpty()) {//TODO: CHECK FOR REDUNDANCY
            return null;
        } else {
            String[] result = res.get(0);
            Amministratore a = new Amministratore();
            a.setIdAmministratore(Integer.parseInt(result[0]));
            a.setUtente_idUtente(Integer.parseInt(result[1]));
            a.setIdUtente(Integer.parseInt(result[1]));
            a.setEmail(u.getEmail());
            a.setPassword(u.getPassword());
            a.setUsername(u.getUsername());
            return a;
        }
    }

    //[Tested with Log-in routine]
    public Compratore findIfUserIsCompratore(String username) {
        Utente u;
        u = this.findByUsername(username); //CHECK IF this.findByUsername IS PROPER USAGE
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idCompratore, Utente_idUtente FROM Compratore WHERE Utente_idUtente = '" + String.valueOf(u.getId()) + "' LIMIT 1;");
        if (res.size() == 0 || res == null || res.isEmpty()) {//TODO: CHECK FOR REDUNDANCY
            return null;
        } else {
            String[] result = res.get(0);
            Compratore c = new Compratore();
            c.setIdCompratore(Integer.parseInt(result[0]));
            c.setUtente_idUtente(Integer.parseInt(result[1]));
            c.setIdUtente(Integer.parseInt(result[1]));
            c.setEmail(u.getEmail());
            c.setPassword(u.getPassword());
            c.setUsername(u.getUsername());

            return c;
        }
    }

    //[Tested with Log-in routine]
    public Venditore findIfUserIsVenditore(String username) {
        Utente u;
        u = this.findByUsername(username); //CHECK IF this.findByUsername IS PROPER USAGE
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idVenditore, Utente_idUtente FROM Venditore WHERE Utente_idUtente = '" + String.valueOf(u.getId()) + "' LIMIT 1;");
        if (res.size() == 0 || res == null || res.isEmpty()) {//TODO: CHECK FOR REDUNDANCY
            return null;
        } else {
            String[] result = res.get(0);
            Venditore v = new Venditore();
            v.setIdVenditore(Integer.parseInt(result[0]));
            v.setUtente_idUtente(Integer.parseInt(result[1]));
            v.setIdUtente(Integer.parseInt(result[1]));
            v.setEmail(u.getEmail());
            v.setPassword(u.getPassword());
            v.setUsername(u.getUsername());

            return v;
        }

    }

    //[Tested with Log-in routine]
    public boolean validateLogin(String username, String password) {
        Utente u;
        u = this.findByUsername(username);
        if (u.getPassword() == password || u.getPassword().equals(password)) { //TODO: THIS IS PROBABLY REDUNDANT
            return true;
        } else {
            return false;
        }
    }

    //TODO: TEST!!
    public Result updatePassword(Utente u, String password){
        Result r = new Result();
        String query = "UPDATE Utente SET password = '" + password + "' WHERE idUtente = '" + String.valueOf(u.getId()) + "';";
        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query);
        if (operation == true) {
            r.setMessage("Password updated.");
            r.setSuccess(true);
            return r;
        } else {
            r.setMessage("Couldn't update password.");
            r.setSuccess(false);
            return r;
        }
    }
}
