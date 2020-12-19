package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Rating;

import java.util.ArrayList;

public interface IRatingDAO extends IBaseDAO<Rating> {
    public ArrayList<Rating> getCumulativeRating(Beni b);
}
