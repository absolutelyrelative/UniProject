package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.Tipo_BeneBusiness;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TipoBeneListener implements ActionListener {
    JTextField add;
    JTextField rmv;

    public TipoBeneListener(JTextField add, JTextField rmv) {
        this.add = add;
        this.rmv = rmv;
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Aggiungi Tipo Bene")) {
            Result result = new Result();
            Utente utente = SessionHelper.getInstance().getUser();

            if (utente != null) {
                Amministratore userIsAdmin = AmministratoreBusiness.getInstance().findIfUserIsAdmin(utente.getUsername());
                if (userIsAdmin != null) {
                    Tipo_Bene tipo_bene = new Tipo_Bene();
                    tipo_bene.setNome(add.getText());
                    tipo_bene.setAmministratore_idAmministratore(userIsAdmin.getIdAmministratore());
                    result = Tipo_BeneBusiness.getInstance().createTB(tipo_bene);//Tipo_Bene ha Primary Key(id, Nome), non ci dovrebbero essere conflitti.
                    if (result.isSuccess()) {
                        JOptionPane.showMessageDialog(null, "Tipo Bene aggiunto.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossibile aggiungere Tipo Bene.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Non è stato possibile trovare l'Amministratore associato all'Utente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Non è stato possibile trovare l'Utente dalla sessione.");
            }
        }
        if ((e.getActionCommand()).equals("Rimuovi Tipo Bene")) {
            Result result = new Result();
            ArrayList<Tipo_Bene> lista_tb = Tipo_BeneBusiness.getInstance().retrieveAll();
            Tipo_Bene tipoBenetoRemove = new Tipo_Bene();

            boolean found = false;
            for (Tipo_Bene tipo_bene : lista_tb) {
                if (tipo_bene.getNome().equals(rmv.getText())) {
                    found = true;
                    tipoBenetoRemove = tipo_bene;
                    result = Tipo_BeneBusiness.getInstance().delete(tipoBenetoRemove);
                    if (result.isSuccess()) {
                        JOptionPane.showMessageDialog(null, "Tipo Bene rimosso."); //TODO: Immetti ON CASCADE delete?
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Non è stato possibile rimuovere Tipo Bene. Controlla il nome.");
                        return;
                    }
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Non è stato possibile rimuovere Tipo Bene. Controlla il nome.");
                return;
            }
        }
    }
}
