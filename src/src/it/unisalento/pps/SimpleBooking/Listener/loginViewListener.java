package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.adminView;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginViewListener implements ActionListener {

    JTextField username;
    JPasswordField password;
    JLabel result;

    public loginViewListener(JTextField username, JPasswordField password,JLabel result) {
        this.username = username;
        this.password = password;
        this.result = result;
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Log in")) {
            Result r;
            String actual_password = String.valueOf(password.getPassword()); //JPasswordField.getText() è deprecato
            r = UtenteBusiness.getInstance().login(username.getText(), actual_password);
            if (r.isSuccess() == true) {
                JOptionPane.showMessageDialog(null, "Log-in effettuato.");
                Utente u = SessionHelper.getInstance().getUser();
                if(u != null) {
                    result.setText("Log-in effettuato. Benvenuto, " + u.getUsername());
                    Amministratore a = UtenteDAO.getInstance().findIfUserIsAdmin(u.getUsername());
                    if(a != null && a.getId() != 0){ //redundant but why not. Ricorda che comunque null non può essere, e nel db, gli ID autogenerati partono da 1.
                        new adminView();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Errore: Controlla le credenziali.");
            }
        }
    }

}
