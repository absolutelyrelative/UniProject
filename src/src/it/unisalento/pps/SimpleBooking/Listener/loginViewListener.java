package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.Model.Amministratore;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.adminView;
import it.unisalento.pps.SimpleBooking.buyerView;
import it.unisalento.pps.SimpleBooking.sellerView;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginViewListener implements ActionListener {

    JTextField username;
    JPasswordField password;
    JLabel result;
    JButton Seller_View;
    JButton Admin_View;
    JButton Buyer_View;

    public loginViewListener(JTextField username, JPasswordField password, JLabel result, JButton Seller_View, JButton Admin_View, JButton Buyer_View) {
        this.username = username;
        this.password = password;
        this.result = result;
        this.Seller_View = Seller_View;
        this.Admin_View = Admin_View;
        this.Buyer_View = Buyer_View;
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Log in")) {
            Result result;
            String actual_password = String.valueOf(password.getPassword()); //JPasswordField.getText() è deprecato
            result = UtenteBusiness.getInstance().login(username.getText(), actual_password);
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(null, "Log-in effettuato.");
                Utente utente = SessionHelper.getInstance().getUser();
                if (utente != null) {
                    this.result.setText("Log-in effettuato. Benvenuto, " + utente.getUsername());
                    Seller_View.setVisible(true);
                    Seller_View.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new sellerView();
                        }
                    });
                    Buyer_View.setVisible(true);
                    Buyer_View.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new buyerView();
                        }
                    });
                    Amministratore userIsAdmin = UtenteDAO.getInstance().findIfUserIsAdmin(utente.getUsername());
                    if (userIsAdmin != null && userIsAdmin.getId() != 0) { //redundant but why not. Ricorda che comunque null non può essere, e nel db, gli ID autogenerati partono da 1.
                        Admin_View.setVisible(true);
                        Admin_View.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new adminView();
                            }
                        });
                    } else {
                        Admin_View.setVisible(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Errore: Controlla le credenziali.");
            }
        }
    }

}
