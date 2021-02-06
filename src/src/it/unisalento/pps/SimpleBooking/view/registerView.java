package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.registerViewListener;

import javax.swing.*;
import java.awt.*;

public class registerView extends JPanel {
    public JPanel c;
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField rg_username = new JTextField(10);
    JTextField rg_email = new JTextField(10);
    JButton rg_button = new JButton("Register");

    public registerView() {
        c = new JPanel();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Username: "));
        c.add(rg_username);
        c.add(new JLabel("E-Mail: "));
        c.add(rg_email);
        c.add(rg_button);

        registerViewListener listener = new registerViewListener(rg_username,rg_email);
        rg_button.addActionListener(listener);

    }

    //PER GERARCHIA DI SWING
    public JPanel returnPane() {
        return c;
    }

    public String getUsernameInput() {
        return rg_username.getText();
    }

    public String getEMailInput() {
        return rg_email.getText();
    }
}
