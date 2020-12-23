package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IVenditoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;

public class VenditoreDAO implements IVenditoreDAO {
    private static VenditoreDAO instance;

    public static VenditoreDAO getInstance() {
        if (instance == null)
            instance = new VenditoreDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Venditore findById(int id) {
        Venditore a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT U.idUtente, U.email, U.password, U.username, Ven.idVenditore, Ven.Utente_idUtente FROM Utente as U INNER JOIN Venditore as Ven ON Ven.Utente_idUtente = U.IdUtente WHERE Ven.idVenditore = '" + id + "' LIMIT 1;");

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        //DEBUG RESULT
        String[] result = res.get(0);
        a = new Venditore();
        a.setIdUtente(Integer.parseInt(result[0]));
        a.setEmail(result[1]);
        a.setPassword(result[2]);
        a.setUsername(result[3]);
        a.setIdVenditore(Integer.parseInt(result[4]));
        a.setUtente_idUtente(Integer.parseInt(result[5]));

        return a;
    }

    //TODO: TEST
    @Override
    public ArrayList<Venditore> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idVenditore FROM Venditore;");
        ArrayList<Venditore> venditori = new ArrayList<>();

        for (String[] row : res) {
            Venditore a = findById(Integer.parseInt(row[0]));
            venditori.add(a);
        }

        return venditori;
    }

    //TODO: TEST
    @Override
    public void create(Venditore a) {
        int idUtente = a.getId();

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Venditore(Utente_idUtente) VALUES('" + idUtente + "');");

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
    public void delete(Venditore a) {
        int idVenditore_td = a.getIdVenditore();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Venditore WHERE idVenditore = '" + idVenditore_td + "';");
    }
}
