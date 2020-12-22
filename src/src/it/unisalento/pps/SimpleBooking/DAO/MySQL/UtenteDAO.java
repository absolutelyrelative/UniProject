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

    //TESTED THROUGH FINDALL() METHOD
    @Override
    public Utente findById(int id) {
        Utente a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idUtente, email, password, username FROM Utente WHERE idUtente = '" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        //DEBUG RESULT
        String[] result = res.get(0);
        a = new Utente();
        a.setIdUtente(Integer.parseInt(result[0]));
        // System.out.println("IdUtente: " + a.getId());
        a.setEmail(result[1]);
        // System.out.println("\neMail: " + a.getEmail());
        a.setPassword(result[2]);
        // System.out.println("\nPassword: " + a.getPassword());
        a.setUsername(result[3]);
        // System.out.println("\nUsername: " + a.getUsername());

        return a;
    }

    //TESTED
    @Override
    public ArrayList<Utente> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idUtente FROM Utente;");
        ArrayList<Utente> utenti = new ArrayList<>(); //new ArrayList<Amministratori>()

        for (String[] row : res) {
            Utente a = findById(Integer.parseInt(row[0]));
            utenti.add(a);
        }

        return utenti;
    }

    //TESTED
    @Override
    public void create(Utente a) {
        int idUtente = a.getId();
        String email = a.getEmail();
        String password = a.getPassword();
        String username = a.getUsername();

        //DB COHERENCY CHECK
        if(email.length() > 45 || password.length() > 45 || username.length() > 45){
            //TODO: Throw error
        }
        else{
            DbConnection.getInstance().eseguiAggiornamento("insert into Utente (idUtente, email, password, username) values ('" + idUtente + "', '"+ email +"', '"+password+"', '"+username+"');");
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

    //TESTED
    @Override
    public void delete(Utente a) {
        int idUtente_td = a.getId();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Utente WHERE idUtente = '" + idUtente_td + "';"); //TODO: Test
    }
}