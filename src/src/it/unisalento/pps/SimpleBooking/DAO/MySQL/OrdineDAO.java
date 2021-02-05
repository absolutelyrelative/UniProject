package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IOrdineDAO;
import it.unisalento.pps.SimpleBooking.Model.Line_Item;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class OrdineDAO implements IOrdineDAO {

    private static OrdineDAO instance;

    public static OrdineDAO getInstance() {
        if (instance == null)
            instance = new OrdineDAO();
        return instance;
    }


    //TODO: TEST
    @Override
    public Ordine findById(int id) {
        Ordine a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idOrdine, Compratore_idCompratore, Importo_Tot FROM Ordine WHERE idOrdine = '" + id + "' LIMIT 1;");

        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Ordine();
            a.setIdOrdine(Integer.parseInt(result[0]));
            a.setCompratore_idCompratore(Integer.parseInt(result[1]));
            a.setImporto_Tot(Float.parseFloat(result[2]));
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
    public ArrayList<Ordine> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idOrdine FROM Ordine;");
        ArrayList<Ordine> ordini = new ArrayList<>();

        for (String[] row : res) {
            Ordine a = findById(Integer.parseInt(row[0]));
            ordini.add(a);
        }

        return ordini;

    }

    //TODO: Test
    @Override
    public Result create(Ordine t) {
        Result r = new Result();
        //int IdImmagine = t.getIdImmagine();
        int Compratore_idCompratore = t.getCompratore_idCompratore();
        float Importo_Tot = 0; //INIITIAL VALUE, UPDATE WITH closeOrderGetCumulativeCost

        boolean operation = DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Ordine(Compratore_idCompratore, Importo_Tot) VALUES('" + Compratore_idCompratore
                + "','" + Importo_Tot + "';"); //TODO: Test
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }


    @Override
    public Result delete(Ordine t) {
        Result r = new Result();

        int IdOrdine_td = t.getIdOrdine();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Ordine WHERE idOrdine = '" + IdOrdine_td + "';");

        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }


    //TODO: TEST
    @Override
    public void closeOrder(Ordine o) {
        float cumulative_cost = 0;
        ArrayList<Line_Item> items = Line_ItemDAO.getInstance().getRelatedItems(o);
        for (Line_Item u : items) {
            cumulative_cost = cumulative_cost + u.getCosto();
        }

        String query = "UPDATE Ordine SET Importo_Tot = '" + cumulative_cost + "' WHERE idOrdine = '" + o.getIdOrdine() + "';";
        DbConnection.getInstance().eseguiAggiornamento(query);

        o.setImporto_Tot(cumulative_cost);

    }

    //TODO: TEST
    @Override
    public float getTotCost(Ordine o) {
        String query = "SELECT Importo_Tot FROM Ordine WHERE idOrdine = '" + o.getIdOrdine() + "' LIMIT 1;";
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery(query);

        String[] result = res.get(0);
        return Float.parseFloat(result[0]);


    }
}
