package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.ICompratoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;

public class CompratoreDAO implements ICompratoreDAO {
    private static CompratoreDAO instance;

    public static CompratoreDAO getInstance() {
        if (instance == null)
            instance = new CompratoreDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Compratore findById(int id) {
        Compratore a = null;
        
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT U.idUtente, U.email, U.password, U.username, Com.idCompratore, Com.Utente_idUtente FROM Utente as U INNER JOIN Compratore as Com ON Com.Utente_idUtente = U.IdUtente WHERE Com.idCompratore = '" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        //DEBUG RESULT
        String[] result = res.get(0);
        a = new Compratore();
        a.setIdUtente(Integer.parseInt(result[0]));
        a.setEmail(result[1]);
        a.setPassword(result[2]);
        a.setUsername(result[3]);
        a.setIdCompratore(Integer.parseInt(result[4]));
        a.setUtente_idUtente(Integer.parseInt(result[5]));

        return a;
    }

    //TODO: TEST
    @Override
    public ArrayList<Compratore> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idCompratore FROM Compratore;");
        ArrayList<Compratore> compratori = new ArrayList<>();

        for (String[] row : res) {
            Compratore a = findById(Integer.parseInt(row[0]));
            compratori.add(a);
        }

        return compratori;
    }

    //TODO: TEST
    @Override
    public void create(Compratore a) {
        int idUtente = a.getId();

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Compratore(Utente_idUtente) VALUES('" + idUtente + "');");

    }

    @Override
    public void create(int id) {
        //UNUSED
    }

    @Override
    public void delete(int id) {
        //UNUSED
    }

    //TODO: TEST
    @Override
    public void delete(Compratore a) {
        int idCompratore_td = a.getIdCompratore();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Compratore WHERE idCompratore = '" + idCompratore_td + "';");
    }
}
