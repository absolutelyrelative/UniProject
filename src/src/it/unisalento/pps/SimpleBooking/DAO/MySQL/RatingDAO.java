package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IRatingDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Rating;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;

public class RatingDAO implements IRatingDAO {
    private static RatingDAO instance;

    public static RatingDAO getInstance() {
        if (instance == null)
            instance = new RatingDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Rating findById(int id) {
        Rating r = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idRating, Rating, Beni_idBeni, Compratore_idCompratore FROM Rating WHERE idRating = '" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        String[] result = res.get(0);
        r = new Rating();
        r.setIdRating(Integer.parseInt(result[0]));
        r.setRating(Integer.parseInt(result[1]));
        r.setBeni_idBeni(Integer.parseInt(result[2]));
        r.setCompratore_idCompratore(Integer.parseInt(result[3]));

        return r;
    }

    //TODO: TEST
    @Override
    public ArrayList<Rating> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idRating FROM Rating;");
        ArrayList<Rating> ratings = new ArrayList<>();

        for (String[] row : res) {
            Rating r = findById(Integer.parseInt(row[0]));
            ratings.add(r);
        }

        return ratings;
    }

    //TODO: TEST
    @Override
    public void create(Rating r) {
        //int idRating = r.getIdRating();
        int Rating = r.getIdRating();
        int Beni_idBeni = r.getBeni_idBeni();
        int Compratore_idCompratore = r.getCompratore_idCompratore();
        String query = "INSERT INTO Rating(Rating, Beni_idBeni, Compratore_idCompratore VALUES('" + Rating + "','" + Beni_idBeni + "','" + Compratore_idCompratore + "');";
        DbConnection.getInstance().eseguiAggiornamento(query);
    }


    //TODO: TEST
    @Override
    public void delete(Rating r) {
        int idRating_td = r.getIdRating();
        String query = "DELETE FROM Rating WHERE idRating = '" + idRating_td + "';";
        DbConnection.getInstance().eseguiAggiornamento(query);

    }

    @Deprecated
    public void create(int id) {

    }

    @Deprecated
    public void delete(int id) {

    }

    //TODO: TEST
    @Override
    public ArrayList<Rating> getCumulativeRating(Beni b) {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idRating FROM Rating WHERE Beni_idBeni = '" + b.getIdBeni() + "';");
        ArrayList<Rating> ratings = new ArrayList<>();

        for (String[] row : res) {
            Rating r = findById(Integer.parseInt(row[0]));
            ratings.add(r);
        }

        return ratings;

    }
}
