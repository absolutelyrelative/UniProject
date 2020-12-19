package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;

public interface ITipo_BeneDAO extends IBaseDAO<Tipo_Bene> {
    public void updateTipoBene(Tipo_Bene t);
}
