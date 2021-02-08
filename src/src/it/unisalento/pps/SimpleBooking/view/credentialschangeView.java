package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.credentialschangeViewListener;

import javax.swing.*;
import java.awt.*;

public class credentialschangeView extends JFrame {

    JLabel reset_description = new JLabel("Cambia la tua password");

    JLabel changeusername_description = new JLabel("Username:");
    JLabel oldpw_description = new JLabel("Password vecchia:");
    JLabel newpw_description = new JLabel("Password nuova:");
    JTextField change_username = new JTextField(10);
    JPasswordField oldpw = new JPasswordField(10);
    JPasswordField newpw = new JPasswordField(10);
    JButton change_button = new JButton("Cambia Password");


    public credentialschangeView() {
        setLayout(new BorderLayout(10, 10));
        setForeground(new Color(86, 214, 120));
        JPanel center = new JPanel();
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel west = new JPanel();
        reset_description.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        north.add(reset_description);
        center.add(changeusername_description);
        center.add(change_username);
        center.add(oldpw_description);
        center.add(oldpw);
        center.add(newpw_description);
        center.add(newpw);
        center.add(change_button);


        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);


        //Listeners
        credentialschangeViewListener listener = new credentialschangeViewListener(change_username, oldpw, newpw);
        change_button.addActionListener(listener);

    }
}
