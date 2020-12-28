package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IPagamentoDAO;
import it.unisalento.pps.SimpleBooking.Model.Pagamento;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;

import java.util.ArrayList;

public class PagamentoDAO implements IPagamentoDAO {
    private static PagamentoDAO instance;

    public static PagamentoDAO getInstance() {
        if (instance == null)
            instance = new PagamentoDAO();
        return instance;
    }

    //TODO: TEST
    @Override
    public Pagamento findById(int id) {
        Pagamento a = null;

        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idPagamento, Stato, Ordine_idOrdine, Numero_Carta, CVV, PIN, Importo FROM Pagamento WHERE idPagamento = '" + id + "' LIMIT 1;");

        if (res.size() != 1) {
            //TODO: Throw Exception, should never happen
        }

        //DEBUG RESULT
        String[] result = res.get(0);
        a = new Pagamento();
        a.setIdPagamento(Integer.parseInt(result[0]));
        a.setStato(Integer.parseInt(result[1]));
        a.setOrdine_idOrdine(Integer.parseInt(result[2]));
        a.setNumero_Carta(result[3]);
        a.setCVV(result[4]);
        a.setPIN(result[5]);
        a.setImporto(Float.parseFloat(result[6]));

        return a;

    }

    //TODO: TEST
    @Override
    public ArrayList<Pagamento> findAll() {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idPagamento FROM Pagamento;");
        ArrayList<Pagamento> pagamenti = new ArrayList<>();

        for (String[] row : res) {
            Pagamento a = findById(Integer.parseInt(row[0]));
            pagamenti.add(a);
        }

        return pagamenti;

    }

    //TODO: TEST
    @Override
    public void create(Pagamento t) {
        //int idLine_Item = t.getIdLine();
        int Stato = t.getStato();
        int Ordine_idOrdine = t.getOrdine_idOrdine();
        String Numero_Carta = t.getNumero_Carta();
        String CVV = t.getCVV();
        String PIN = t.getPIN();
        float importo = t.getImporto();

        String query = "INSERT INTO Pagamento(Stato, Ordine_idOrdine, Numero_Carta, CVV, PIN, Importo) VALUES('" + Stato + "','" + Ordine_idOrdine + "','" + Numero_Carta + "','" + CVV + "','" + PIN + "','" + importo + "');";

        DbConnection.getInstance().eseguiAggiornamento(query); //TODO: Test

    }


    //TODO: TEST
    //TODO: TEST CASCADE DELETE OF ORDINE -> PAGAMENTO
    @Override
    public void delete(Pagamento t) {
        int idPagamento_td = t.getIdPagamento();
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Pagamento WHERE idPagamento = '" + idPagamento_td + "';");

    }


    @Deprecated
    @Override
    public void create(int id) {

    }

    @Deprecated
    @Override
    public void delete(int id) {

    }

    //TODO: TEST
    @Override
    public void updatePaymentStato(Pagamento p, int status) {
        //DATA COHERENCY CHECK
        //TODO: THROW ARITHMETIC ERROR?
        if(status >= 1)
            status = 1;
        else
            status = 0;

        String query = "UPDATE Pagamento SET Stato = '" + status + "' WHERE idTipo_Bene = '" + p.getIdPagamento() + "';";
        DbConnection.getInstance().eseguiAggiornamento(query);
    } //0 = Not Paid, 1 = Paid


}
