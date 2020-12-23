package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IImmagineDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.Model.Immagine;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

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

    }

    //TODO: TEST
    @Override
    public Immagine findById(int id) {
        Immagine a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idImmagine, Beni_idBeni FROM Immagine WHERE idImmagine = '" + id + "' LIMIT 1;");

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        //DEBUG RESULT
        String[] result = res.get(0);
        a = new Immagine();
        a.setIdImmagine(Integer.parseInt(result[0]));
        a.setData(DbConnection.getInstance().getFoto("SELECT Data FROM Immagine WHERE idImmagine = '"+ a.getIdImmagine() + "';")); //TODO: Test Images
        a.setBeni_idBeni(Integer.parseInt(result[1]));

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


    @Override
    void create(Immagine t) {

    }


    @Override
    void delete(Immagine t) {

    }

    @Override
    void create(int id) {

    }

    @Override
    void delete(int id) {

    }
}
