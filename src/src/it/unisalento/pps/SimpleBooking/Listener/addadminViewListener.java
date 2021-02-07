package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
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
            Result r;
            r = AmministratoreBusiness.getInstance().createAmministratore(username.getText());
            if (r.isSuccess() == true) {
                JOptionPane.showMessageDialog(null, "Amministratore aggiunto.");
            } else {
                JOptionPane.showMessageDialog(null, "Impossibile aggiungere amministratore. Controlla che non lo sia già, e che l'username sia corretto.");
            }
        }
    }
}
