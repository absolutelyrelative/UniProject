package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.registerViewListener;

import javax.swing.*;
import java.awt.*;

public class registerView extends JPanel {
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField rg_username = new JTextField(10);
    JTextField rg_email = new JTextField(10);
    JButton rg_button = new JButton("Register");

    public registerView() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        setLayout(flowLayout);
        setBackground(new Color(200, 221, 242));
        add(new JLabel("Username: "));
        add(rg_username);
        add(new JLabel("E-Mail: "));
        add(rg_email);
        add(rg_button);
        add(new JLabel("Inserisci username e e-mail. La password sarà inviata sulla tua E-Mail."));

        registerViewListener listener = new registerViewListener(rg_username, rg_email);
        rg_button.addActionListener(listener);

    }


    public String getUsernameInput() {
        return rg_username.getText();
    }

    public String getEMailInput() {
        return rg_email.getText();
    }

}
