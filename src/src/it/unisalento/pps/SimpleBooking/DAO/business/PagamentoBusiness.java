package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.PagamentoDAO;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.Model.Pagamento;
import it.unisalento.pps.SimpleBooking.util.Result;

public class PagamentoBusiness {
    private static PagamentoBusiness instance;

    public static synchronized PagamentoBusiness getInstance() {
        if (instance == null)
            instance = new PagamentoBusiness();
        return instance;
    }

    private PagamentoBusiness() {
    }

    public Result generatePagamento(int beni_id){
        Result r = new Result();
        Ordine o = OrdineDAO.getInstance().getOrdineFromBeni(beni_id);
        Pagamento p = new Pagamento();
        p.setStato(0); //0 - Unpaid, 1 - Paid
        p.setOrdine_idOrdine(o.getIdOrdine());
        p.setImporto(o.getImporto_Tot());

        Result c = PagamentoDAO.getInstance().create(p);
        if(c.isSuccess()){
            r.setSuccess(true);
            r.setMessage("Istanza di pagamento creata.");
            return r;
        }
        else{
            r.setSuccess(false);
            r.setMessage("Non è stato possibile creare un'istanza di pagamento nel DB.");
            return r;
        }
    }

}
