package it.unisalento.pps.SimpleBooking.DAO.business;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Compratore;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.MailHelper;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OrdineBusiness {
    private static OrdineBusiness instance;

    public static synchronized OrdineBusiness getInstance() {
        if (instance == null)
            instance = new OrdineBusiness();
        return instance;
    }

    private OrdineBusiness() {
    }

    public Result createOrdine(Date inizio, Date fine, Beni b) {
        Result r = new Result();
        //Create order first
        Ordine o = new Ordine();

        //Calcolo giorni per calcolo costo
        long differenza = fine.getTime() - inizio.getTime();
        long giorni = TimeUnit.DAYS.convert(differenza, TimeUnit.MILLISECONDS) + 1; //Minimum of 1 days is paid
        if (giorni <= Long.MAX_VALUE && giorni >= Long.MIN_VALUE) {   //Per evitare overflow o incoerenze
            //Calcolo costo
            float costo = (float) giorni * b.getCosto_pd();
            o.setImporto_Tot(costo);
            Compratore c = UtenteDAO.getInstance().findIfUserIsCompratore(SessionHelper.getInstance().getUser().getUsername());
            if (c != null) {
                o.setCompratore_idCompratore(c.getIdCompratore());
                o.setData_Inizio(inizio);
                o.setData_Fine(fine);
                o.setBeni_idBeni(b.getIdBeni());
                o.setBeni_Nome(b.getNome());
                Result k = OrdineDAO.getInstance().create(o);
                if (k.isSuccess()) {
                    r.setSuccess(true);
                    r.setMessage("Ordine Creato");
                    return r;
                } else {
                    r.setSuccess(false);
                    r.setMessage("Errore durante esecuzione query.");
                    return r;
                }
            } else {
                r.setSuccess(false);
                r.setMessage("Compratore not found.");
                return r;
            }
        } else {
            r.setSuccess(false);
            r.setMessage("long is too long."); // :D
            return r;
        }


    }

    public boolean isOrdered(int beni_id) {
        //Questa è probabilmente contro GDPR lol
        //ArrayList<Ordine> ordini = OrdineDAO
        //Infatti, ho scritto un metodo in OrdineDAO per evitare problemi legali (e di risorse)
        return OrdineDAO.getInstance().isOrdered(beni_id);

    }

    //Send alert to Beni's owner if an order is placed
    public Result sendAlert(int beni_id) {
        Result r = new Result();
        Utente u = BeniBusiness.getInstance().getOwnerofBeni(beni_id);
        if (u != null) {
            String email = u.getEmail();
            Beni b = BeniDAO.getInstance().findById(beni_id);
            new MailHelper().send(email, "SimpleBooking: Ordine effettuato", "Ciao. Il tuo bene " + b.getNome() + " è stato ordinato. Riceverai presto i dettagli.");
            r.setSuccess(true);
            r.setMessage("E-Mail inviata.");
            return r;
        } else {
            r.setSuccess(false);
            r.setMessage("Utente non trovato.");
            return r;
        }

    }
}
