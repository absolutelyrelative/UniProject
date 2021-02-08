package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class credentialschangeViewListener implements ActionListener {
    JTextField username;
    JPasswordField old_pw;
    JPasswordField new_pw;

    public credentialschangeViewListener(JTextField username, JPasswordField old_pw, JPasswordField new_pw) {
        this.username = username;
        this.old_pw = old_pw;
        this.new_pw = new_pw;
    }


    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Cambia Password")) {
            Result r = new Result();
            String old_password = String.valueOf(old_pw.getPassword()); //JPasswordField.getText() è deprecato
            String new_password = String.valueOf(new_pw.getPassword()); //JPasswordField.getText() è deprecato
            Utente u = UtenteDAO.getInstance().findByUsername(username.getText());
            if (u != null) {
                r = UtenteBusiness.getInstance().changePassword(username.getText(), old_password, new_password);
                if (old_password.isEmpty() || old_password == null || old_password == "" || new_password.isEmpty() || new_password == null || new_password == "") {
                    JOptionPane.showMessageDialog(null, "Immetti delle password non vuote.");
                } else {
                    if (r.isSuccess() == true) {
                        JOptionPane.showMessageDialog(null, "La password è stata cambiata.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossibile cambiare la password. Controlla che le credenziali siano corrette.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Non è stato possibile trovare l'utente associato all'username dato.");
            }

        }

    }
}
