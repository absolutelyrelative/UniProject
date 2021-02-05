package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.ILine_ItemDAO;
import it.unisalento.pps.SimpleBooking.Model.Line_Item;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class Line_ItemDAO implements ILine_ItemDAO {

    private static Line_ItemDAO instance;

    public static Line_ItemDAO getInstance() {
        if (instance == null)
            instance = new Line_ItemDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Line_Item findById(int id) {
        Line_Item a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idLine_Item, Ordine_idOrdine, Beni_idBeni, Data_sel_Inizio, Data_sel_Fine, Costo FROM Line_Item WHERE idLine_Item = '" + id + "' LIMIT 1;");

        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Line_Item();
            a.setIdLine(Integer.parseInt(result[0]));
            a.setOrdine_idOrdine(Integer.parseInt(result[1]));
            a.setBeni_idBeni(Integer.parseInt(result[2]));
            a.setData_sel_Inizio(new java.sql.Date(Long.parseLong(result[3]))); //TODO: TEST DATE TYPE
            a.setData_sel_Fine(new java.sql.Date(Long.parseLong(result[4]))); //TODO: TEST DATE TYPE
            a.setCosto(Float.parseFloat(result[5]));
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
    public ArrayList<Line_Item> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idLine_Item FROM Line_Item;");
        ArrayList<Line_Item> Line_Items = new ArrayList<>();

        for (String[] row : res) {
            Line_Item a = findById(Integer.parseInt(row[0]));
            Line_Items.add(a);
        }

        return Line_Items;

    }

    //TODO: TEST
    @Override
    public Result create(Line_Item t) {
        Result r = new Result();
        //int idLine_Item = t.getIdLine();
        int Ordine_idOrdine = t.getOrdine_idOrdine();
        int Beni_idBeni = t.getBeni_idBeni();
        java.sql.Date Data_sel_Inizio = t.getData_sel_Inizio();
        java.sql.Date Data_sel_Fine = t.getData_sel_Fine();
        float Costo = t.getCosto();

        String query = "INSERT INTO Line_Item(Ordine_idOrdine, Beni_idBeni, Data_sel_Inizio, Data_sel_Fine, Costo) VALUES('" + Ordine_idOrdine + "','" + Beni_idBeni + "'," +
                "'" + Data_sel_Inizio + "','" + Data_sel_Fine + "','" + Costo + "');";


        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query); //TODO: Test
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }


    //TODO: TEST
    @Override
    public Result delete(Line_Item t) {
        Result r = new Result();
        int idLine_Item_td = t.getIdLine();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Line_Item WHERE idLine_Item = '" + idLine_Item_td + "';");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }


    //TODO: TEST
    //UNUSED?
    @Override
    public float getCumulativeCost(Line_Item LI) {
        return 0;
    }

    //TODO: TEST
    @Override
    public ArrayList<Line_Item> getRelatedItems(Ordine o) {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idLine_Item FROM Line_Item WHERE Ordine_idOrdine = '" + o.getIdOrdine() + "';");
        ArrayList<Line_Item> Line_Items = new ArrayList<>();

        for (String[] row : res) {
            Line_Item a = findById(Integer.parseInt(row[0]));
            Line_Items.add(a);
        }

        return Line_Items;

    }
}
