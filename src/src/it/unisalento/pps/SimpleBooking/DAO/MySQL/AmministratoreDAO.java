package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IAmministratoreDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;

public class AmministratoreDAO implements IAmministratoreDAO {

    private static AmministratoreDAO instance;

    public static AmministratoreDAO getInstance() {
        if (instance == null)
            instance = new AmministratoreDAO();
        return instance;
    }

    @Override
    public Amministratore findById(int id){
        Amministratore a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT * FROM ... WHERE ID=" +id+";");

        if(res.size()==1){
            //TODO: Use Result
        }
    }

    @Override
    public ArrayList<Amministratore> findAll(){return null;}

    @Override
    public void create(Amministratore a){}

    @Override
    public void delete(Amministratore a){}
}
