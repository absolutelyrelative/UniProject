package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.ICompratoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

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


        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Compratore();
            a.setIdUtente(Integer.parseInt(result[0]));
            a.setEmail(result[1]);
            a.setPassword(result[2]);
            a.setUsername(result[3]);
            a.setIdCompratore(Integer.parseInt(result[4]));
            a.setUtente_idUtente(Integer.parseInt(result[5]));
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }


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
    public Result create(Compratore a) {
        Result r = new Result();
        int idUtente = a.getId();

        boolean operation = DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Compratore(Utente_idUtente) VALUES('" + idUtente + "');");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }

    //TODO: TEST
    @Override
    public Result delete(Compratore a) {
        Result r = new Result();
        int idCompratore_td = a.getIdCompratore();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Compratore WHERE idCompratore = '" + idCompratore_td + "';");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }

}
