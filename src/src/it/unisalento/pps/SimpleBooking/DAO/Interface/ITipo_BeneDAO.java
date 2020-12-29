package it.unisalento.pps.SimpleBooking.DAO.Interface;

import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;

public interface ITipo_BeneDAO extends IBaseDAO<Tipo_Bene> {
    void updateTipoBene(Tipo_Bene t_old, String name);
}
