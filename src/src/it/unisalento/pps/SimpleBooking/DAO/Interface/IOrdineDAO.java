package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Ordine;

public interface IOrdineDAO extends IBaseDAO<Ordine> {

    void closeOrder(Ordine o);

    float getTotCost(Ordine o);

}
