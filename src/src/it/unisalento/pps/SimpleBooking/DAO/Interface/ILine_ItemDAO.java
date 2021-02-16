package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Line_Item;
import it.unisalento.pps.SimpleBooking.Model.Ordine;

import java.util.ArrayList;

public interface ILine_ItemDAO extends IBaseDAO<Line_Item> {
    float getCumulativeCost(Line_Item LI);

    ArrayList<Line_Item> getRelatedItems(Ordine o);
}
