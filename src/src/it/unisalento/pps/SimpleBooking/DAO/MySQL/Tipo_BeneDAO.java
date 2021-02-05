package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.ITipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

import java.util.ArrayList;

public class Tipo_BeneDAO implements ITipo_BeneDAO {

    private static Tipo_BeneDAO instance;

    public static Tipo_BeneDAO getInstance() {
        if (instance == null)
            instance = new Tipo_BeneDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Tipo_Bene findById(int id) {
        Tipo_Bene a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idTipo_Bene, Nome, Amministratore_idAmministratore FROM Tipo_Bene WHERE idTipo_Bene = '" + id + "' LIMIT 1;");

        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Tipo_Bene();
            a.setIdTipo(Integer.parseInt(result[0]));
            a.setNome(result[1]);
            a.setAmministratore_idAmministratore(Integer.parseInt(result[2]));
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        } finally {
            if (res.size() != 1) {
                System.out.println("Out of bounds.\n");
            }
        }


        return a;

    }

    //TODO: TEST
    @Override
    public ArrayList<Tipo_Bene> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idTipo_Bene FROM Tipo_Bene;");
        ArrayList<Tipo_Bene> Tipi_Bene = new ArrayList<>();

        for (String[] row : res) {
            Tipo_Bene a = findById(Integer.parseInt(row[0]));
            Tipi_Bene.add(a);
        }

        return Tipi_Bene;

    }

    //TODO: TEST
    @Override
    public Result create(Tipo_Bene t) {
        Result r = new Result();
        //int idLine_Item = t.getIdLine();
        String nome = t.getNome();
        int Amministratore_idAmministratore = t.getAmministratore_idAmministratore();

        String query = "INSERT INTO Tipo_Bene(Nome, Amministratore_idAmministratore) VALUES('" + nome + "','" + Amministratore_idAmministratore + "');";


        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query); //TODO: Test
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }


    //TODO: TEST
    //TODO: TEST SQL'S CASCADE DELETE OF TIPO_BENE -> BENE
    @Override
    public Result delete(Tipo_Bene t) {
        Result r = new Result();
        int idTipo_Bene_td = t.getIdTipo();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Tipo_Bene WHERE idTipo_Bene = '" + idTipo_Bene_td + "';");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }


    //TODO: TEST
    @Override
    public void updateTipoBene(Tipo_Bene t_old, String name) {
        String query = "UPDATE Tipo_Bene SET Nome = '" + name + "' WHERE idTipo_Bene = '" + t_old.getIdTipo() + "';";
        DbConnection.getInstance().eseguiAggiornamento(query);

    }
}
