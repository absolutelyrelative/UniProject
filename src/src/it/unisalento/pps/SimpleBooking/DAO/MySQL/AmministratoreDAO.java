package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IAmministratoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;


public class AmministratoreDAO implements IAmministratoreDAO {

    private static AmministratoreDAO instance;

    public static AmministratoreDAO getInstance() {
        if (instance == null)
            instance = new AmministratoreDAO();
        return instance;
    }

    @Override
    public Amministratore findById(int id) {
        Amministratore a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT U.idUtente, U.email, U.password, U.username, Adm.idAmministratore, Adm.Utente_idUtente FROM Utente as U INNER JOIN Amministratore as Adm ON Adm.Utente_idUtente = U.IdUtente WHERE Adm.idAmministratore = '" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1


        try {
            String[] result = res.get(0);
            a = new Amministratore();
            a.setIdUtente(Integer.parseInt(result[0]));
            a.setEmail(result[1]);
            a.setPassword(result[2]);
            a.setUsername(result[3]);
            a.setIdAmministratore(Integer.parseInt(result[4]));
            a.setUtente_idUtente(Integer.parseInt(result[5]));


        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }//DEBUG RESULT
        return a;
    }

    @Override
    public ArrayList<Amministratore> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT Adm.idAmministratore FROM Amministratore AS Adm;");
        ArrayList<Amministratore> amministratori = new ArrayList<>(); //new ArrayList<Amministratori>()

        for (String[] row : res) {
            Amministratore a = findById(Integer.parseInt(row[0]));
            amministratori.add(a);
        }

        return amministratori;
    }

    //TODO: WORKS, BUT DOES NOT CHECK IF ANOTHER SAME UTENTE_IDUTENTE EXISTS!!
    //In order to create Amministratore, you must first create a user. (?)
    @Override
    public Result create(Amministratore a) {
        Result r = new Result();
        int IdUtente = a.getUtente_idUtente();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Amministratore(Utente_idUtente) VALUES('" + IdUtente + "');");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }


    @Override
    public Result delete(Amministratore a) {
        Result r = new Result();
        int idAmministratore_td = a.getIdAmministratore();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Amministratore WHERE idAmministratore = '" + idAmministratore_td + "';"); //TODO: Test
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }

    //TODO: REMOVE, NOT USED
    public Result create(int IdAdmin, int IdUtente) {
        Result r = new Result();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Amministratore(idAmministratore,Utente_idUtente) VALUES('" + IdAdmin + "','" + IdUtente + "');");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }
}
