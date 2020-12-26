package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IBeniDAO;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;
import java.util.Date;

public class BeniDAO implements IBeniDAO {

    private static BeniDAO instance;

    public static BeniDAO getInstance() {
        if (instance == null)
            instance = new BeniDAO();
        return instance;
    }

    @Override
    public Beni findById(int id) {
        Beni a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idBeni, Nome, Descrizione, Data_Inizio, Data_Fine, Costo_pw, Costo_pm, Costo_pd, GPS_Lat, GPS_Lon, Addr, Venditore_idVenditore, Tipo_Bene_idTipo_Bene, Stato_Bene, Pubblicazione, Amministratore_idAmministratore FROM Beni WHERE idBeni = '" + id + "' LIMIT 1;"); //TODO: Test LIMIT 1

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }


        String[] result = res.get(0);
        a = new Beni();
        a.setIdBeni(Integer.parseInt(result[0]));
        a.setDescrizione(result[1]);
        a.setData_Inizio(new java.sql.Date(Long.parseLong(result[2]))); //TODO: TEST DATE TYPE
        a.setData_Fine(new java.sql.Date(Long.parseLong(result[3]))); //TODO: TEST DATE TYPE
        a.setCosto_pw(Float.parseFloat(result[4]));
        a.setCosto_pm(Float.parseFloat(result[5]));
        a.setCosto_pd(Float.parseFloat(result[6]));
        a.setGPS_Lat(Float.parseFloat(result[7]));
        a.setGPS_Lon(Float.parseFloat(result[8]));
        a.setAddr(result[9]);
        a.setVenditore_idVenditore(Integer.parseInt(result[10]));
        a.setTipo_Bene_idTipo_Bene(Integer.parseInt(result[11]));
        a.setStato_Bene(Integer.parseInt(result[12]));
        a.setPubblicazione(Integer.parseInt(result[13]));
        a.setAmministratore_idAmministratore(Integer.parseInt(result[14]));


        return a;

    }

    @Override
    public ArrayList<Beni> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idBeni FROM Beni;");
        ArrayList<Beni> beni = new ArrayList<>();

        for (String[] row : res) {
            Beni a = findById(Integer.parseInt(row[0]));
            beni.add(a);
        }

        return beni;

    }


    //TODO: CALCULATE COSTS PM,PW,PD HERE. ON SETTING, VISUALIZE ERROR IF MORE THAN ONE INTERVAL CHOSEN BY USER
    @Override
    public void create(Beni b) {
        float Costo_pd = b.getCosto_pd();
        float Costo_pw = b.getCosto_pw();
        float Costo_pm = b.getCosto_pm();


        //TODO: PERHAPS THIS CALCULATION BELONGS IN THE GUI VIEW?
        if (Costo_pd != 0.0f) {
            Costo_pw = Costo_pd * 7;
            Costo_pm = Costo_pw * 4.35f; //TODO: TEST, THIS IS AN ESTIMATE
        }
        if (Costo_pw != 0.0f) {
            Costo_pd = Costo_pw / 7;
            Costo_pm = Costo_pw * 4.35f;
        }
        if (Costo_pm != 0.0f) {
            Costo_pw = Costo_pm / 4.35f;
            Costo_pd = Costo_pw / 7;
        }

        //TODO: ADD COHERENCY TEST FOR LENGHT IN GUI VIEW
        int idBeni = b.getIdBeni();
        String nome = b.getNome();
        String descrizione = b.getDescrizione();
        java.sql.Date Data_Inizio = b.getData_Inizio();
        java.sql.Date Data_Fine = b.getData_Fine();
        float GPS_Lat = b.getGPS_Lat();
        float GPS_Lon = b.getGPS_Lon();
        String Addr = b.getAddr();
        int Venditore_idVenditore = b.getVenditore_idVenditore();
        int Tipo_Bene_idTipo_Bene = b.getTipo_Bene_idTipo_Bene();
        int Stato_Bene = b.getStato_Bene();
        int Pubblicazione = b.getPubblicazione();
        int Amministratore_idAmministratore = b.getAmministratore_idAmministratore();

        String query = "INSERT INTO Beni(idBeni, Nome, Descrizione, Data_Inizio, Data_Fine, Costo_pw, Costo_pm, Costo_pd" +
                ", GPS_Lat, GPS_Lon, Addr, Venditore_idVenditore, Tipo_Bene_idTipo_Bene, Stato_Bene, Pubblicazione, " +
                "Amministratore_idAmministratore) VALUES('" + idBeni + "', '" + nome + "','" + descrizione + "','" + Data_Inizio + "','" + Data_Fine + "','" + GPS_Lat + "','" + GPS_Lon + "','" + Addr + "'," +
                "'" + Venditore_idVenditore + "','" + Tipo_Bene_idTipo_Bene + "','" + Stato_Bene + "','" + Pubblicazione + "','" + Amministratore_idAmministratore + "');";
        DbConnection.getInstance().eseguiAggiornamento(query);

    }

    //TODO: TEST 'ON DELETE CASCADE' SETTING ON SQL
    @Override
    public void delete(Beni b) {
        int idBeni_td = b.getIdBeni();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Beni WHERE idBeni = '" + idBeni_td + "';"); //TODO: Test

    }

    @Override
    @Deprecated
    public void create(int id) {

    }

    @Override
    @Deprecated
    public void delete(int id) {

    }

    @Override
    public ArrayList<Beni> sortByDate(Date Inizio, Date Fine) {

    }

    @Override
    public ArrayList<Beni> sortByCost(Float cost, int type) {

    } //Type 0 = per day, type 1 = per week, type 2 = per month

    @Override
    public ArrayList<Beni> sortByCreator(Utente u) {

    } //Used to create Mini Catalogue for Venditore

    @Override
    public void publishBene(Beni b) {

    }

    @Override
    public void updateBene(Beni b) {

    } //TODO: Works with 'stato bene', is necessary to mark a bene as ordered, among other things. Break into smaller parts maybe?
}
