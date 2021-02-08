package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
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
            Result r = new Result();
            Utente u = SessionHelper.getInstance().getUser();

            if (u != null) {
                Amministratore a = UtenteDAO.getInstance().findIfUserIsAdmin(u.getUsername());
                if (a != null) {
                    Tipo_Bene tb = new Tipo_Bene();
                    tb.setNome(add.getText());
                    tb.setAmministratore_idAmministratore(a.getIdAmministratore());
                    r = Tipo_BeneDAO.getInstance().create(tb); //Tipo_Bene ha Primary Key(id, Nome), non ci dovrebbero essere conflitti.
                    if (r.isSuccess() == true) {
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
            Result r = new Result();
            ArrayList<Tipo_Bene> lista_tb = Tipo_BeneDAO.getInstance().findAll();
            Tipo_Bene tb_td = new Tipo_Bene();

            boolean found = false;
            for (Tipo_Bene tb : lista_tb) {
                if (tb.getNome().equals(rmv.getText())) {
                    found = true;
                    tb_td = tb;
                    r = Tipo_BeneDAO.getInstance().delete(tb_td);
                    if (r.isSuccess() == true) {
                        JOptionPane.showMessageDialog(null, "Tipo Bene rimosso."); //TODO: Immetti ON CASCADE delete?
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Non è stato possibile rimuovere Tipo Bene. Controlla il nome.");
                        return;
                    }
                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(null, "Non è stato possibile rimuovere Tipo Bene. Controlla il nome.");
                return;
            }
        }
    }
}
