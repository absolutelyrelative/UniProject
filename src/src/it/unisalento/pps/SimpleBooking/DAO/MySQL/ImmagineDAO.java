package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IImmagineDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ImmagineDAO implements IImmagineDAO {

    private static ImmagineDAO instance;

    public static ImmagineDAO getInstance() {
        if (instance == null)
            instance = new ImmagineDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public ArrayList<Immagine> getImagesFromBene(Beni b) {
        int IdBene = b.getIdBeni();
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idImmagine FROM Immagine WHERE Beni_idBeni = '" + IdBene + "';");
        ArrayList<Immagine> immagini = new ArrayList<>();

        for (String[] row : res) {
            Immagine a = findById(Integer.parseInt(row[0]));
            immagini.add(a);
        }

        return immagini;

    }

    //TODO: TEST
    @Override
    public Immagine findById(int id) {
        Immagine a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idImmagine, Beni_idBeni FROM Immagine WHERE idImmagine = '" + id + "' LIMIT 1;");

        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Immagine();
            a.setIdImmagine(Integer.parseInt(result[0]));
            a.setData(DbConnection.getInstance().getFoto("SELECT Data FROM Immagine WHERE idImmagine = '" + a.getIdImmagine() + "';")); //TODO: Test Images
            a.setBeni_idBeni(Integer.parseInt(result[1]));
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
    public ArrayList<Immagine> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idImmagine FROM Immagine;");
        ArrayList<Immagine> immagini = new ArrayList<>();

        for (String[] row : res) {
            Immagine a = findById(Integer.parseInt(row[0]));
            immagini.add(a);
        }

        return immagini;

    }

    //TODO: Test
    @Override
    public Result create(Immagine t) {
        Result r = new Result();
        //int IdImmagine = t.getIdImmagine();
        byte[] blob = t.getData();
        int IdBeni = t.getBeni_idBeni();

        boolean operation = DbConnection.getInstance().eseguiAggiornamento("INSERT INTO Immagine(Data, Beni_idBeni) VALUES('" + blob
                + "','" + IdBeni + "';"); //TODO: Test
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }


    @Override
    public Result delete(Immagine t) {
        Result r = new Result();
        int IdImmagine_td = t.getIdImmagine();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Immagine WHERE idImmagine = '" + IdImmagine_td + "';");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;
    }


}
