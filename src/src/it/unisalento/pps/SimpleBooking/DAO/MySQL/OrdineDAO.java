package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IOrdineDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Line_Item;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idOrdine, Compratore_idCompratore, Importo_Tot, Data_Inizio, Data_Fine, Beni_idBeni, Beni_Nome FROM Ordine WHERE idOrdine = '" + id + "' LIMIT 1;");

        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Ordine();
            a.setIdOrdine(Integer.parseInt(result[0]));
            a.setCompratore_idCompratore(Integer.parseInt(result[1]));
            a.setImporto_Tot(Float.parseFloat(result[2]));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date data_inizio = format.parse(result[3]);
            Date data_fine = format.parse(result[4]);
            a.setData_Inizio(data_inizio);
            a.setData_Fine(data_fine);
            a.setBeni_idBeni(Integer.parseInt(result[5]));
            a.setBeni_Nome(result[6]);
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } catch (ParseException e) {
            e.printStackTrace();
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
        Date Data_Inizio = t.getData_Inizio();
        Date Data_Fine = t.getData_Fine();
        Calendar inizio_calendar = new GregorianCalendar();
        inizio_calendar.setTime(Data_Inizio);
        int inizio_year = inizio_calendar.get(Calendar.YEAR);
        int inizio_month = inizio_calendar.get(Calendar.MONTH) + 1;
        int inizio_day = inizio_calendar.get(Calendar.DAY_OF_MONTH);

        Calendar fine_calendar = new GregorianCalendar();
        fine_calendar.setTime(Data_Fine);
        int fine_year = fine_calendar.get(Calendar.YEAR);
        int fine_month = fine_calendar.get(Calendar.MONTH) + 1;
        int fine_day = fine_calendar.get(Calendar.DAY_OF_MONTH);

        boolean operation = DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Ordine(Compratore_idCompratore, Importo_Tot, Data_Inizio, Data_Fine, Beni_idBeni, Beni_Nome) " +
                "VALUES('" + t.getCompratore_idCompratore() + "','" + t.getImporto_Tot() + "','" + inizio_year + "-" + inizio_month + "-" + inizio_day + "','" + fine_year + "-" + fine_month + "-" + fine_day + "','" + t.getBeni_idBeni() + "','" + t.getBeni_Nome() + "');");
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


    @Deprecated
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

    @Deprecated
    //TODO: TEST
    @Override
    public float getTotCost(Ordine o) {
        String query = "SELECT Importo_Tot FROM Ordine WHERE idOrdine = '" + o.getIdOrdine() + "' LIMIT 1;";
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery(query);

        String[] result = res.get(0);
        return Float.parseFloat(result[0]);


    }

    public boolean isOrdered(int id) {
        String query = "SELECT idOrdine FROM Ordine WHERE Beni_idBeni = '" + id + "'";
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery(query);
        if (res.isEmpty() || res.size() == 0 || res == null) {
            return false;
        } else {
            return true;
        }
    }
}
