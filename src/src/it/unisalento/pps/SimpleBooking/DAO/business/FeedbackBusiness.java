package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.FeedbackDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
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

    private ArrayList<Feedback> getFeedbackfromBeniId(int id) {
        ArrayList<Feedback> all = FeedbackDAO.getInstance().findAll();
        ArrayList<Feedback> sorted = new ArrayList<>();

        for (Feedback f : all) {
            if (f.getBeni_idBeni() == id) {
                sorted.add(f);
            }
        }
        return sorted;
    }

    public ArrayList<Comment> getFormattedFeedbackfromBeniId(int id) {
        ArrayList<Feedback> sorted = this.getFeedbackfromBeniId(id);
        ArrayList<Comment> formatted = new ArrayList<>();
        ArrayList<Integer> toDelete = new ArrayList<>();

        if (!sorted.isEmpty()) {  //It shouldn't be necessary to check if sorted == null, given we initialised it in the function above.
            for (Feedback f : sorted) {
                Comment c = new Comment();

                //Get all parents
                if (f.getFeedback_idFeedback() == 0) {
                    //Case 1 - Feedback is parent
                    c.setParent_feedback(f);
                    if(f.getRating() == 0xFF){
                        c.setParent_node(String.valueOf("(" + f.getIdFeedback() + ") " + "[No Rating] " + f.getCommento()));
                    }
                    else{
                        c.setParent_node(String.valueOf("(" + f.getIdFeedback() + ") " + "[Rating: " + f.getRating()) + "/5] " + f.getCommento());
                    }

                    formatted.add(c);
                    toDelete.add(sorted.indexOf(f));
                }
            }
        }

        for (Integer i : toDelete) {
            sorted.remove(i);
        }
        toDelete.clear();

        //Sorted now contains a list of children comment, and formatted contains a list of parent comments
        if (!formatted.isEmpty()) {
            for (Feedback f : sorted) {
                for (Comment c : formatted) {
                    if (f.getFeedback_idFeedback() == c.getParent_feedback().getIdFeedback()) {
                        //Child comment!
                        c.setChild_feedback(f);
                        c.setChild_node("(" + f.getIdFeedback() + ") " + f.getCommento());
                        toDelete.add(sorted.indexOf(f));
                    }
                }
            }
        }
        for (Integer i : toDelete) {
            sorted.remove(i);
        }
        toDelete.clear();

        return formatted;
    }

    public Result deleteReplyToFeedback(DefaultMutableTreeNode node){
        Result r = new Result();
        String original_comment = (String) node.getUserObject();
        String sorted = original_comment.replaceAll(".*\\(|\\).*", "");
        int id_toReplyTo = Integer.parseInt(sorted);
        Feedback original = FeedbackDAO.getInstance().findById(id_toReplyTo);
        if(original != null){
            Feedback old_reply = FeedbackDAO.getInstance().getReplytoReply(original);
            if(old_reply != null){
                Result c = FeedbackDAO.getInstance().delete(old_reply);
                if(c.isSuccess()){
                    r.setSuccess(true);
                    r.setMessage("Risposta a feedback eliminata.");
                    return r;
                }
                else{
                    r.setSuccess(false);
                    r.setMessage("Errore durante esecuzione query.");
                    return r;
                }
            }
            else{
                r.setSuccess(false);
                r.setMessage("Risposta a feedback non trovata.");
                return r;
            }
        }
        else{
            r.setSuccess(false);
            r.setMessage("Feedback non trovato.");
            return r;
        }
    }

    public Result replyToFeedback(DefaultMutableTreeNode node, String s){
        Result r = new Result();
        String original_comment = (String) node.getUserObject();
        String sorted = original_comment.replaceAll(".*\\(|\\).*", "");
        int id_toReplyTo = Integer.parseInt(sorted);
        if(s.length() > comment_maxLength){
            r.setSuccess(false);
            r.setMessage("Commento troppo lungo.");
            return r;
        }
        else{
            Feedback original = FeedbackDAO.getInstance().findById(id_toReplyTo);
            if(original != null){
                Feedback f = new Feedback();
                f.setRating(0xFF); //Default value for no-rating in DB, TODO: REMOVE? ABOVE METHOD ALREADY DOES NOT SHOW RATINGS
                f.setFeedback_idFeedback(id_toReplyTo);
                f.setCommento(s);
                f.setBeni_idBeni(original.getBeni_idBeni());
                Venditore v = UtenteDAO.getInstance().findIfUserIsVenditore(SessionHelper.getInstance().getUser().getUsername());
                if(v != null){
                    f.setVenditore_idVenditore(v.getIdVenditore());
                    f.setCompratore_idCompratore(original.getCompratore_idCompratore());
                    FeedbackDAO.getInstance().create(f);
                    r.setSuccess(true);
                    r.setMessage("Feedback inviato.");
                    return r;
                }
                else{
                    r.setSuccess(false);
                    r.setMessage("Venditore non trovato.");
                    return r;
                }
            }
            else{
                r.setSuccess(false);
                r.setMessage("Feedback non trovato.");
                return r;
            }
        }

    }

}
