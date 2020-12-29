package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Feedback;

import java.util.ArrayList;

public interface IFeedbackDAO extends IBaseDAO<Feedback> {
    ArrayList<Feedback> getRepliestoBeni(Beni b);

    ArrayList<Feedback> getRepliestoReply(Feedback f);
}
