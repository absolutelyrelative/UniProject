package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.TipoBeneListener;
import it.unisalento.pps.SimpleBooking.Listener.credentialsresetViewListener;

import javax.swing.*;
import java.awt.*;

public class credentialsresetView extends JFrame {

    JLabel reset_description = new JLabel("Richiedi reset della password");

    JLabel username_description = new JLabel("Username:");
    JTextField reset_username = new JTextField(10);
    JButton reset_button = new JButton("Reset Password");

    /*
        JLabel change_description = new JLabel("Cambia password");
    JLabel changeusername_description = new JLabel("Username:");
    JLabel oldpw_description = new JLabel("Password vecchia:");
    JLabel newpw_description = new JLabel("Password nuova:");
    JTextField change_username = new JTextField(10);
    JPasswordField oldpw = new JPasswordField(10);
    JPasswordField newpw = new JPasswordField(10);
    JButton change_button = new JButton("Cambia Password");*/


    public credentialsresetView() {
        setLayout(new BorderLayout(10, 10));
        setForeground(new Color(86, 214, 120));
        JPanel center = new JPanel();
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel west = new JPanel();
        reset_description.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        north.add(reset_description);
        center.add(username_description);
        center.add(reset_username);
        center.add(reset_button);


        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);


        //Listeners
        credentialsresetViewListener listener = new credentialsresetViewListener(reset_username);
        reset_button.addActionListener(listener);

    }
}
