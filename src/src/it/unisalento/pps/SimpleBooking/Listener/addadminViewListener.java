package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addadminViewListener implements ActionListener {
    JTextField username;

    public addadminViewListener(JTextField username) {
        this.username = username;
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Aggiungi Admin")) {
            Amministratore a = AmministratoreBusiness.getInstance().findIfUserIsAdmin(username.getText());
            if (a == null) {
                Result r;
                r = AmministratoreBusiness.getInstance().createAmministratore(username.getText());
                if (r.isSuccess() == true) {
                    JOptionPane.showMessageDialog(null, "Amministratore aggiunto.");
                } else {
                    JOptionPane.showMessageDialog(null, "Impossibile aggiungere amministratore. Controlla che non lo sia già, e che l'username sia corretto.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Impossibile aggiungere amministratore poiché l'user specificato lo è già.");
            }
        }
        if ((e.getActionCommand()).equals("Rimuovi Admin")) {
            Result r;
            r = AmministratoreBusiness.getInstance().deleteAmministratore(username.getText());
            if (r.isSuccess() == true) {
                JOptionPane.showMessageDialog(null, "Amministratore rimosso.");
            } else {
                JOptionPane.showMessageDialog(null, "Impossibile rimuovere amministratore. Controlla che non lo sia già, e che l'username sia corretto.");
            }
        }
    }
}
