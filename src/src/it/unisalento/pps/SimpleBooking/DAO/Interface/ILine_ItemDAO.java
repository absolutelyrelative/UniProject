package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Line_Item;

public interface ILine_ItemDAO extends IBaseDAO<Line_Item> {
    public float getCumulativeCost(Line_Item LI);
}
