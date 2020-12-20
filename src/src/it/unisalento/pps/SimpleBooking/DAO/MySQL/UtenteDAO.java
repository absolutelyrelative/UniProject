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

    @Override
    public Utente findById(int id) {
        Utente a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT * FROM Utente WHERE idUtente='" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        String[] result = res.get(0);
        a = new Utente();
        a.setIdUtente(Integer.parseInt(result[0]));
        a.setEmail();

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
