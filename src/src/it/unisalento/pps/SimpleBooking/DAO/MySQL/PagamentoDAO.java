package it.unisalento.pps.SimpleBooking.DAO.MySQL;

import it.unisalento.pps.SimpleBooking.DAO.Interface.IPagamentoDAO;
import it.unisalento.pps.SimpleBooking.Model.Pagamento;
import it.unisalento.pps.SimpleBooking.dbInterface.DbConnection;
import it.unisalento.pps.SimpleBooking.util.Result;

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

        try {//DEBUG RESULT
            String[] result = res.get(0);
            a = new Pagamento();
            a.setIdPagamento(Integer.parseInt(result[0]));
            a.setStato(Integer.parseInt(result[1]));
            a.setOrdine_idOrdine(Integer.parseInt(result[2]));
            a.setNumero_Carta(result[3]);
            a.setCVV(result[4]);
            a.setPIN(result[5]);
            a.setImporto(Float.parseFloat(result[6]));
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
    public Result create(Pagamento t) {
        Result r = new Result();
        //int idLine_Item = t.getIdLine();
        int Stato = t.getStato();
        int Ordine_idOrdine = t.getOrdine_idOrdine();
        String Numero_Carta = t.getNumero_Carta();
        String CVV = t.getCVV();
        String PIN = t.getPIN();
        float importo = t.getImporto();

        String query = "INSERT INTO Pagamento(Stato, Ordine_idOrdine, Numero_Carta, CVV, PIN, Importo) VALUES('" + Stato + "','" + Ordine_idOrdine + "','" + Numero_Carta + "','" + CVV + "','" + PIN + "','" + importo + "');";

        boolean operation = DbConnection.getInstance().eseguiAggiornamento(query); //TODO: Test
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }


    //TODO: TEST
    //TEST CASCADE DELETE OF ORDINE -> PAGAMENTO (Works!)
    @Override
    public Result delete(Pagamento t) {
        Result r = new Result();
        int idPagamento_td = t.getIdPagamento();
        boolean operation = DbConnection.getInstance().eseguiAggiornamento("DELETE FROM Pagamento WHERE idPagamento = '" + idPagamento_td + "';");
        if (operation) {
            r.setSuccess(true);
        } else {
            r.setSuccess(false);
        }
        return r;

    }


    //TODO: TEST
    @Override
    public void updatePaymentStato(Pagamento p, int status) {
        //DATA COHERENCY CHECK
        //TODO: THROW ARITHMETIC ERROR?
        if (status >= 1)
            status = 1;
        else
            status = 0;

        String query = "UPDATE Pagamento SET Stato = '" + status + "' WHERE idTipo_Bene = '" + p.getIdPagamento() + "';";
        DbConnection.getInstance().eseguiAggiornamento(query);
    } //0 = Not Paid, 1 = Paid


    public Pagamento getPagamentoFromOrderId(int order_id) {
        ArrayList<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT idPagamento FROM Pagamento WHERE Ordine_idOrdine = '" + order_id + "' LIMIT 1;");

        Pagamento a = null;

        if (res.isEmpty() || res.size() == 0 || res == null) {
            return null;
        } else {
            String[] row = res.get(0);
            a = findById(Integer.parseInt(row[0]));
        }

        return a;
    }

    //Probabilmente viola qualche legge sulla privacy ma vabé
    public Result pay(Pagamento p, String Card, String CVV, String Pin) {
        Result r = new Result();
        String query = "UPDATE Pagamento SET Stato = '1', Numero_Carta = '" + Card + "', CVV = '" + CVV + "', PIN = '" + Pin + "' WHERE idPagamento = '" + p.getIdPagamento() + "';";
        if(DbConnection.getInstance().eseguiAggiornamento(query) == true){
            r.setSuccess(true);
            r.setMessage("Query pagamento eseguita.");
            return r;
        }
        else{
            r.setSuccess(false);
            r.setMessage("Problema con query pagamento.");
            return r;
        }
    }

}
