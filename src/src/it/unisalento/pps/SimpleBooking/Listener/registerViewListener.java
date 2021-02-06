package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.view.registerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerViewListener implements ActionListener {
    /*
    button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You just clicked button");
            }
        });
     */
    JTextField username;
    JTextField email;

    public registerViewListener(JTextField username, JTextField email) {
        this.username = username;
        this.email = email;
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Register")) {
            Result r;
            r = UtenteBusiness.getInstance().register(username.getText(), email.getText());
            if (r.isSuccess() == true) {
                JOptionPane.showMessageDialog(null, "Registrazione effettuata. E-Mail con password spedita.");
            } else {
                JOptionPane.showMessageDialog(null, "Esiste già un utente con queste credenziali. Puoi richiedere il reset della password se necessario.");
            }
        }
    }
}
