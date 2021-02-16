package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IFeedbackDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Feedback;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class FeedbackDAO implements IFeedbackDAO {
    private static FeedbackDAO instance;

    public static FeedbackDAO getInstance() {
        if (instance == null)
            instance = new FeedbackDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Feedback findById(int id) {
        Feedback r = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idFeedback, Commento, Feedback_idFeedback, Beni_idBeni, Compratore_idCompratore, Venditore_idVenditore, Rating FROM Feedback WHERE idFeedback = '" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        try {
            String[] result = res.get(0);
            r = new Feedback();
            r.setIdFeedback(Integer.parseInt(result[0]));
            r.setCommento(result[1]); //TODO: TEST STRING TYPE COHERENCY
            if ((result[2]) == null) { //PARENT
                r.setBeni_idBeni(Integer.parseInt(result[3]));
                r.setCompratore_idCompratore(Integer.parseInt(result[4]));
                if (result[5] == null) { //Può esserlo se è PARENT
                    r.setRating(Integer.parseInt(result[6]));
                } else {
                    r.setVenditore_idVenditore(Integer.parseInt(result[5]));
                    r.setRating(Integer.parseInt(result[6]));
                }
            } else { //CHILD
                r.setFeedback_idFeedback(Integer.parseInt(result[2]));
                r.setBeni_idBeni(Integer.parseInt(result[3]));
                r.setCompratore_idCompratore(Integer.parseInt(result[4]));
                r.setVenditore_idVenditore(Integer.parseInt(result[5]));
                r.setRating(Integer.parseInt(result[6]));
            }
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }


        return r;
    }

    //TODO: TEST
    @Override
    public ArrayList<Feedback> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idFeedback FROM Feedback;");
        ArrayList<Feedback> feedback = new ArrayList<>();

        for (String[] row : res) {
            Feedback r = findById(Integer.parseInt(row[0]));
            feedback.add(r);
        }

        return feedback;
    }

    //TODO: TEST
    @Override
    public Result create(Feedback r) {
        Result c = new Result();
        //int idFeedback = r.getIdFeedback();
        String Commento = r.getCommento(); //TODO: TEST STRING TYPE COHERENCY
        int Feedback_idFeedback = r.getFeedback_idFeedback(); //TODO: TEST IF NULL VALUE
        int Beni_idBeni = r.getBeni_idBeni();
        int Compratore_idCompratore = r.getCompratore_idCompratore();
        int Venditore_idVenditore = r.getVenditore_idVenditore();
        int Rating = r.getRating();
        String query = "INSERT INTO Feedback(Commento, Feedback_idFeedback, Beni_idBeni, Compratore_idCompratore, Venditore_idVenditore, Rating) VALUES ('" + Commento + "','" + Feedback_idFeedback + "','" + Beni_idBeni + "','" + Compratore_idCompratore + "','" + Venditore_idVenditore + "','" + Rating + "');";
        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query);
        if (operation) {
            c.setSuccess(true);
        } else {
            c.setSuccess(false);
        }
        return c;
    }

    public Result createRoot(Feedback r) {
        Result c = new Result();
        //int idFeedback = r.getIdFeedback();
        String Commento = r.getCommento(); //TODO: TEST STRING TYPE COHERENCY
        int Beni_idBeni = r.getBeni_idBeni();
        int Compratore_idCompratore = r.getCompratore_idCompratore();
        int Venditore_idVenditore = r.getVenditore_idVenditore();
        int Rating = r.getRating();
        String query = "INSERT INTO Feedback(Commento, Beni_idBeni, Compratore_idCompratore, Rating) VALUES ('" + Commento + "','" + Beni_idBeni + "','" + Compratore_idCompratore + "','" + Rating + "');";
        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query);
        if (operation) {
            c.setSuccess(true);
        } else {
            c.setSuccess(false);
        }
        return c;
    }


    //TODO: TEST
    //TODO: TEST CASCADE DELETE OF CHILD/PARENT FEEDBACK
    @Override
    public Result delete(Feedback r) {
        Result c = new Result();
        int idFeedback_td = r.getIdFeedback();
        String query = "DELETE FROM Feedback WHERE idFeedback = '" + idFeedback_td + "';";
        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query);
        if (operation) {
            c.setSuccess(true);
        } else {
            c.setSuccess(false);
        }
        return c;

    }


    //TODO: TEST
    public ArrayList<Feedback> getRepliestoBeni(Beni b) {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idFeedback FROM Feedback WHERE Beni_idBeni = '" + b.getIdBeni() + "';");
        ArrayList<Feedback> feedback = new ArrayList<>();

        for (String[] row : res) {
            Feedback r = findById(Integer.parseInt(row[0]));
            feedback.add(r);
        }

        return feedback;

    }

    //TODO: TEST
    public ArrayList<Feedback> getRepliestoReply(Feedback f) {
        //CHILDREN FEEDBACK HAVE Feedback_idFeedback SET TO PARENT'S idFeedback
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idFeedback FROM Feedback WHERE Feedback_idFeedback = '" + f.getIdFeedback() + "';");
        ArrayList<Feedback> feedback = new ArrayList<>();

        for (String[] row : res) {
            Feedback r = findById(Integer.parseInt(row[0]));
            feedback.add(r);
        }

        return feedback;


    }

    //TODO: TEST!!
    //PER REQUISITI, AL RISPOSTA E' UNICA
    public Feedback getReplytoReply(Feedback f) {
        //CHILDREN FEEDBACK HAVE Feedback_idFeedback SET TO PARENT'S idFeedback
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idFeedback, Commento, Feedback_idFeedback, Beni_idBeni, Compratore_idCompratore, Venditore_idVenditore, Rating FROM Feedback WHERE Feedback_idFeedback = '" + f.getIdFeedback() + "' LIMIT 1;");
        Feedback r = null;

        try {
            String[] result = res.get(0);
            r = new Feedback();
            r.setIdFeedback(Integer.parseInt(result[0]));
            r.setCommento(result[1]); //TODO: TEST STRING TYPE COHERENCY
            if ((result[2]) == null) { //PARENT
                r.setBeni_idBeni(Integer.parseInt(result[3]));
                r.setCompratore_idCompratore(Integer.parseInt(result[4]));
                r.setVenditore_idVenditore(Integer.parseInt(result[5]));
                r.setRating(Integer.parseInt(result[6]));
            } else { //CHILD
                r.setFeedback_idFeedback(Integer.parseInt(result[2]));
                r.setBeni_idBeni(Integer.parseInt(result[3]));
                r.setCompratore_idCompratore(Integer.parseInt(result[4]));
                r.setVenditore_idVenditore(Integer.parseInt(result[5]));
                r.setRating(Integer.parseInt(result[6]));
            }
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }

        return r;
    }

}
