package it.unisalento.pps.SimpleBooking.view;


import it.unisalento.pps.SimpleBooking.Listener.loginViewListener;

import javax.swing.*;
import java.awt.*;

public class loginView extends JPanel {
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField lg_username;
    JPasswordField lg_password;
    JButton lg_button = new JButton("Log in");
    JLabel result;

    public loginView() {
        setLayout(new FlowLayout());
        setBackground(new Color(200, 221, 242));

        //Components
        result = new JLabel("");
        lg_username = new JTextField(10);
        lg_password = new JPasswordField(10);
        add(new JLabel("Username: "));
        add(lg_username);
        add(new JLabel("Password: "));
        add(lg_password);
        add(lg_button);
        add(result);


        //Listeners
        loginViewListener listener = new loginViewListener(lg_username,lg_password,result);
        lg_button.addActionListener(listener);
    }


}
