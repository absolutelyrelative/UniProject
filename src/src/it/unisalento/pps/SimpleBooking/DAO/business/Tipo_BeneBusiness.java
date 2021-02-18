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

    //r = Tipo_BeneDAO.getInstance().create(tb); //Tipo_Bene ha Primary Key(id, Nome), non ci dovrebbero essere conflitti.
    public Result createTB(Tipo_Bene tb){
        return Tipo_BeneDAO.getInstance().create(tb);
    }

    //ArrayList<Tipo_Bene> lista_tb = Tipo_BeneDAO.getInstance().findAll();
    public ArrayList<Tipo_Bene> retrieveAll(){
        ArrayList<Tipo_Bene> lista_tb = new ArrayList<>();
        lista_tb = Tipo_BeneDAO.getInstance().findAll();
        return lista_tb;
    }

    //r = Tipo_BeneDAO.getInstance().delete(tb_td);
    public Result delete(Tipo_Bene tb){
        return Tipo_BeneDAO.getInstance().delete(tb);
    }
}
