package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.loginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginView extends JPanel {
    public JPanel c;
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField lg_username;
    JPasswordField lg_password;
    JButton lg_button = new JButton("Log in");
    loginListener listener = new loginListener();

    public loginView() {
        c = new JPanel();
        c.setLayout(new FlowLayout());

        //Components
        lg_username = new JTextField(10);
        lg_password = new JPasswordField(10);
        c.add(new JLabel("Username: "));
        c.add(lg_username);
        c.add(new JLabel("Password: "));
        c.add(lg_password);
        c.add(lg_button);

        //Listeners
        lg_button.addActionListener(listener);
        lg_button.setActionCommand(loginListener.login_cmd);

    }

    //PER GERARCHIA DI SWING
    public JPanel returnPane() {
        return c;
    }
}
