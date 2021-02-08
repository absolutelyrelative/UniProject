package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class credentialsresetViewListener implements ActionListener {
    JTextField username;

    public credentialsresetViewListener(JTextField username) {
        this.username = username;
    }


    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Reset Password")) {
            Result r = new Result();
            Utente u = UtenteDAO.getInstance().findByUsername(username.getText());
            if (u != null) {
                r = UtenteBusiness.getInstance().requestPasswordReset(u.getUsername());
                if (r.isSuccess() == true) {
                    JOptionPane.showMessageDialog(null, "La tua nuova password è stata inviata sulla tua E-Mail.");
                } else {
                    JOptionPane.showMessageDialog(null, "Si è verificato un'errore durante il reset della password.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Non è stato possibile trovare l'utente specificato. Controlla che l'username sia corretto.");
            }
        }
    }
}
