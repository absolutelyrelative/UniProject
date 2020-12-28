package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Ordine;

public interface IOrdineDAO extends IBaseDAO<Ordine> {

    public float closeOrderandGetCumulativeCost(Ordine o);

}
