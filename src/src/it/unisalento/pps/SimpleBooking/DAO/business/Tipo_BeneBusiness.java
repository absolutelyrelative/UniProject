package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class Tipo_BeneBusiness {
    private static Tipo_BeneBusiness instance;

    public static synchronized Tipo_BeneBusiness getInstance() {
        if (instance == null)
            instance = new Tipo_BeneBusiness();
        return instance;
    }

    private Tipo_BeneBusiness() {
    }

    //TODO: TEST!!
    public Tipo_Bene getTipoFromName(String name) {
        Tipo_Bene tb = new Tipo_Bene();
        ArrayList<Tipo_Bene> all = Tipo_BeneDAO.getInstance().findAll();
        //Inefficiente, ma più pulito
        for (Tipo_Bene t : all) {
            if (t.getNome().equals(name)) {
                tb.setNome(t.getNome());
                tb.setIdTipo(t.getIdTipo());
                tb.setAmministratore_idAmministratore(t.getAmministratore_idAmministratore());
                return tb;
            }
        }
        return null;
    }

}
