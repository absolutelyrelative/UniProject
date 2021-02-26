package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.FeedbackDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.Model.Feedback;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.util.Comment;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class FeedbackBusiness {
    private static final int comment_maxLength = 150;
    private static FeedbackBusiness instance;

    public static synchronized FeedbackBusiness getInstance() {
        if (instance == null)
            instance = new FeedbackBusiness();
        return instance;
    }

    private FeedbackBusiness() {
    }

    public Result createFeedback(int rating, Beni b, String message){
        Result result = new Result();
        Compratore userIsCompratore = UtenteDAO.getInstance().findIfUserIsCompratore(SessionHelper.getInstance().getUser().getUsername());
        if(userIsCompratore != null){
            Feedback feedback = new Feedback();
            feedback.setBeni_idBeni(b.getIdBeni());
            feedback.setCompratore_idCompratore(userIsCompratore.getIdCompratore());
            feedback.setCommento(message);
            feedback.setRating(rating);
            return FeedbackDAO.getInstance().createRoot(feedback);
        }
        else{
            result.setSuccess(false);
            return result;
        }
    }

    private ArrayList<Feedback> getFeedbackfromBeniId(int id) {
        ArrayList<Feedback> all = FeedbackDAO.getInstance().findAll();
        ArrayList<Feedback> sorted = new ArrayList<>();

        for (Feedback feedback : all) {
            if (feedback.getBeni_idBeni() == id) {
                sorted.add(feedback);
            }
        }
        return sorted;
    }

    public ArrayList<Comment> getFormattedFeedbackfromBeniId(int id) {
        ArrayList<Feedback> sorted = this.getFeedbackfromBeniId(id);
        ArrayList<Comment> formatted = new ArrayList<>();
        ArrayList<Integer> toDelete = new ArrayList<>();

        if (!sorted.isEmpty()) {  //It shouldn't be necessary to check if sorted == null, given we initialised it in the function above.
            for (Feedback feedback : sorted) {
                Comment comment = new Comment();

                //Get all parents
                if (feedback.getFeedback_idFeedback() == 0) {
                    //Case 1 - Feedback is parent
                    comment.setParent_feedback(feedback);
                    if(feedback.getRating() == 0xFF){
                        comment.setParent_node(String.valueOf("(" + feedback.getIdFeedback() + ") " + "[No Rating] " + feedback.getCommento()));
                    }
                    else{
                        comment.setParent_node(String.valueOf("(" + feedback.getIdFeedback() + ") " + "[Rating: " + String.valueOf(feedback.getRating()) + "/5] " + feedback.getCommento()));
                    }

                    formatted.add(comment);
                    toDelete.add(sorted.indexOf(feedback));
                }
            }
        }

        for (Integer integer : toDelete) {
            sorted.remove(integer);
        }
        toDelete.clear();

        //Sorted now contains a list of children comment, and formatted contains a list of parent comments
        if (!formatted.isEmpty()) {
            for (Feedback feedback : sorted) {
                for (Comment comment : formatted) {
                    if (feedback.getFeedback_idFeedback() == comment.getParent_feedback().getIdFeedback()) {
                        //Child comment!
                        comment.setChild_feedback(feedback);
                        comment.setChild_node("(" + feedback.getIdFeedback() + ") " + feedback.getCommento());
                        toDelete.add(sorted.indexOf(feedback));
                    }
                }
            }
        }
        for (Integer integer : toDelete) {
            sorted.remove(integer);
        }
        toDelete.clear();

        return formatted;
    }

    public Result deleteReplyToFeedback(DefaultMutableTreeNode node){
        Result result = new Result();
        String original_comment = (String) node.getUserObject();
        String sorted = original_comment.replaceAll(".*\\(|\\).*", "");
        int id_toReplyTo = Integer.parseInt(sorted);
        Feedback original = FeedbackDAO.getInstance().findById(id_toReplyTo);
        if(original != null){
            Feedback old_reply = FeedbackDAO.getInstance().getReplytoReply(original);
            if(old_reply != null){
                Result deleteResult = FeedbackDAO.getInstance().delete(old_reply);
                if(deleteResult.isSuccess()){
                    result.setSuccess(true);
                    result.setMessage("Risposta a feedback eliminata.");
                    return result;
                }
                else{
                    result.setSuccess(false);
                    result.setMessage("Errore durante esecuzione query.");
                    return result;
                }
            }
            else{
                result.setSuccess(false);
                result.setMessage("Risposta a feedback non trovata.");
                return result;
            }
        }
        else{
            result.setSuccess(false);
            result.setMessage("Feedback non trovato.");
            return result;
        }
    }

    public Result replyToFeedback(DefaultMutableTreeNode node, String commentString){
        Result result = new Result();
        String original_comment = (String) node.getUserObject();
        String sorted = original_comment.replaceAll(".*\\(|\\).*", "");
        int id_toReplyTo = Integer.parseInt(sorted);
        if(commentString.length() > comment_maxLength){
            result.setSuccess(false);
            result.setMessage("Commento troppo lungo.");
            return result;
        }
        else{
            Feedback original = FeedbackDAO.getInstance().findById(id_toReplyTo);
            if(original != null){
                Feedback feedback = new Feedback();
                feedback.setRating(0xFF); //Default value for no-rating in DB, TODO: REMOVE? ABOVE METHOD ALREADY DOES NOT SHOW RATINGS
                feedback.setFeedback_idFeedback(id_toReplyTo);
                feedback.setCommento(commentString);
                feedback.setBeni_idBeni(original.getBeni_idBeni());
                Venditore userIsVenditore = UtenteDAO.getInstance().findIfUserIsVenditore(SessionHelper.getInstance().getUser().getUsername());
                if(userIsVenditore != null){
                    feedback.setVenditore_idVenditore(userIsVenditore.getIdVenditore());
                    feedback.setCompratore_idCompratore(original.getCompratore_idCompratore());
                    FeedbackDAO.getInstance().create(feedback);
                    result.setSuccess(true);
                    result.setMessage("Feedback inviato.");
                    return result;
                }
                else{
                    result.setSuccess(false);
                    result.setMessage("Venditore non trovato.");
                    return result;
                }
            }
            else{
                result.setSuccess(false);
                result.setMessage("Feedback non trovato.");
                return result;
            }
        }

    }

}
