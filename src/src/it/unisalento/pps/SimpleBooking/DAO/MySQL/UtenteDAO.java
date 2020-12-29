package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IUtenteDAO;
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

    //TESTED THROUGH FINDALL() METHOD
    @Override
    public Utente findById(int id) {
        Utente a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idUtente, email, password, username FROM Utente WHERE idUtente = '" + id + "' LIMIT 1;");

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

    //TESTED
    @Override
    public void create(Utente a) {
        //int idUtente = a.getId();
        String email = a.getEmail();
        String password = a.getPassword();
        String username = a.getUsername();

        //DB COHERENCY CHECK
        if (email.length() > 45 || password.length() > 45 || username.length() > 45) {
            //TODO: Throw error
        } else {
            DbConnection.getInstance().eseguiAggiornamento("insert into Utente (email, password, username) values ('" + email + "', '" + password + "', '" + username + "');");
        }
    }

    @Override
    public void create(int id) {
        //UNUSED
    }

    @Override
    public void delete(int id) {
        //UNUSED
    }

    //Cascade delete tested successful on venditore
    @Override
    public void delete(Utente a) {
        int idUtente_td = a.getId();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Utente WHERE idUtente = '" + idUtente_td + "';");
    }
}
