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

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT * FROM Amministratore WHERE idAmministratore='" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        String result[] = res.get(0);
        a = new Amministratore();
        a.setIdAmministratore(Integer.parseInt(result[0]));
        a.setUtente_idUtente(Integer.parseInt(result[1]));

        return a;
    }

    @Override
    public ArrayList<Amministratore> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT * FROM Amministratore");
        ArrayList<Amministratore> amministratori = new ArrayList<>(); //new ArrayList<Amministratori>()

        for (String[] row : res) {
            Amministratore a = findById(Integer.parseInt(row[0]));
            amministratori.add(a);
        }

        return amministratori;
    }

    @Override
    public void create(Amministratore a) {

    }

    @Override
    public void create(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(Amministratore a) {
    }

    public void create(int IdAdmin, int IdUtente) {
        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Amministratore(idAmministratore,Utente_idUtente) VALUES('" + IdAdmin + "','" + IdUtente + "');");
    }
}
