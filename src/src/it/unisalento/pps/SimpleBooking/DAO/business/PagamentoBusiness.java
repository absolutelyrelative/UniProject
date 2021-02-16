package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.PagamentoDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.Model.Pagamento;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.MailHelper;
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

    public Result generatePagamento(int beni_id) {
        Result r = new Result();
        Ordine o = OrdineDAO.getInstance().getOrdineFromBeni(beni_id);
        Pagamento p = new Pagamento();
        p.setStato(0); //0 - Unpaid, 1 - Paid
        p.setOrdine_idOrdine(o.getIdOrdine());
        p.setImporto(o.getImporto_Tot());

        Result c = PagamentoDAO.getInstance().create(p);
        if (c.isSuccess()) {
            r.setSuccess(true);
            r.setMessage("Istanza di pagamento creata.");
            return r;
        } else {
            r.setSuccess(false);
            r.setMessage("Non è stato possibile creare un'istanza di pagamento nel DB.");
            return r;
        }
    }

    public Pagamento getPagamentoFromOrder(Ordine o) {
        return PagamentoDAO.getInstance().getPagamentoFromOrderId(o.getIdOrdine());
    }

    public Result pay(Pagamento p, String Card, String CVV, String Pin) {
        Result r = PagamentoDAO.getInstance().pay(p, Card, CVV, Pin);
        if (r.isSuccess()) {
            Ordine o = OrdineDAO.getInstance().findById(p.getOrdine_idOrdine());
            if (o != null) {
                Beni b = BeniDAO.getInstance().findById(o.getBeni_idBeni());
                if (b != null) {
                    BeniDAO.getInstance().unpublishBene(b);
                    Utente u = BeniBusiness.getInstance().getOwnerofBeni(b.getIdBeni());
                    if (u != null) {
                        new MailHelper().send(u.getEmail(), "SimpleBooking: Pagamento effettuato", "Ciao. Il tuo bene " + b.getNome() + " è stato PAGATO. Il tuo bene è stato tolto dalla pagina pubblica. Puoi crearne uno nuovo se vi risultano date disponibili.");
                        r.setMessage("Pagamento effettuato.");
                        r.setSuccess(true);
                        return r;
                    } else {
                        r.setMessage("Utente non trovato.");
                        r.setSuccess(false);
                        return r;
                    }
                } else {
                    r.setMessage("Bene non trovato.");
                    r.setSuccess(false);
                    return r;
                }
            } else {
                r.setMessage("Ordine non trovato.");
                r.setSuccess(false);
                return r;
            }

        } else {
            r.setMessage("Errore durante fase di pagamento.");
            r.setSuccess(false);
            return r;
        }
    }

}
