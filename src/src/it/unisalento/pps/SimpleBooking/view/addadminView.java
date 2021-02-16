package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.addadminViewListener;
import it.unisalento.pps.SimpleBooking.Listener.loginViewListener;

import javax.swing.*;
import java.awt.*;

//TODO: RETURN TO JPanel ?
public class addadminView extends JFrame {
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField username;
    JButton button = new JButton("Aggiungi Admin");
    JLabel add = new JLabel("Username dell'admin da aggiungere: ");

    public addadminView() {
        setLayout(new BorderLayout());
        setForeground(new Color(86, 214, 120));
        username = new JTextField(10);
        JPanel north = new JPanel();
        JPanel center = new JPanel();
        north.add(add);
        center.add(username);
        center.add(button);

        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);

        //add(add);
        //add(username);
        //add(button);


        //Listeners
        addadminViewListener listener = new addadminViewListener(username);
        button.addActionListener(listener);
    }
}
