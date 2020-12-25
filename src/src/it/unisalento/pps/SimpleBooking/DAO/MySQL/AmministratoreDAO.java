package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IAmministratoreDAO;
import it.unisalento.pps.SimpleBooking.Exceptions.MySQLUnitaryException;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

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

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        //DEBUG RESULT
        String[] result = res.get(0);
        a = new Amministratore();
        a.setIdUtente(Integer.parseInt(result[0]));
        a.setEmail(result[1]);
        a.setPassword(result[2]);
        a.setUsername(result[3]);
        a.setIdAmministratore(Integer.parseInt(result[4]));
        a.setUtente_idUtente(Integer.parseInt(result[5]));

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

    //In order to create Amministratore, you must first create a user. (?)
    @Override
    public void create(Amministratore a) {
        //TODO: IMPLEMENT
        //int IdAdmin = a.getIdAmministratore();
        //int IdUtente = a.getUtente_idUtente();
        //create(IdAdmin,IdUtente);

    }

    @Override
    public void create(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(Amministratore a) {
        int idAmministratore_td = a.getIdAmministratore();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Amministratore WHERE idAmministratore = '" + idAmministratore_td + "';"); //TODO: Test
    }

    public void create(int IdAdmin, int IdUtente) {
        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Amministratore(idAmministratore,Utente_idUtente) VALUES('" + IdAdmin + "','" + IdUtente + "');");
    }
}
