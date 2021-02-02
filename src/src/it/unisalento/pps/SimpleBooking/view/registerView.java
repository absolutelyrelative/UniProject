package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class registerView extends JPanel {
    public JPanel c;
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField rg_username;
    JTextField rg_email;
    JPasswordField rg_password;
    JButton rg_button = new JButton("Register");

    public registerView(){
        c = new JPanel();
        c.setLayout(new FlowLayout());
        rg_username = new JTextField(10);
        rg_email = new JTextField(10);
        rg_password = new JPasswordField(10);
        c.add(new JLabel("Username: "));
        c.add(rg_username);
        c.add(new JLabel("E-Mail: "));
        c.add(rg_email);
        c.add(new JLabel("Password: "));
        c.add(rg_password);
        c.add(rg_button);

    }

    //PER GERARCHIA DI SWING
    public JPanel returnPane(){
        return c;
    }
}
