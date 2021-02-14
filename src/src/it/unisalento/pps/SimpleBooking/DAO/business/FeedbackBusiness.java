package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.FeedbackDAO;
import it.unisalento.pps.SimpleBooking.Model.Feedback;
import it.unisalento.pps.SimpleBooking.util.Comment;

import java.util.ArrayList;

public class FeedbackBusiness {
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
                    c.setParent_node(String.valueOf("[Rating: " + f.getRating()) + "/5] " + f.getCommento());
                    formatted.add(c);
                    toDelete.add(sorted.indexOf(f));
                }
            }
        }

        for(Integer i : toDelete){
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
                        c.setChild_node(f.getCommento());
                        toDelete.add(sorted.indexOf(f));
                    }
                }
            }
        }
        for(Integer i : toDelete){
            sorted.remove(i);
        }
        toDelete.clear();

        return formatted;
    }

}
