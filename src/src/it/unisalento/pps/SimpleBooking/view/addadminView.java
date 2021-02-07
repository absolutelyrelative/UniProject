package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.addadminViewListener;
import it.unisalento.pps.SimpleBooking.Listener.loginViewListener;

import javax.swing.*;
import java.awt.*;

public class addadminView extends JFrame {
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField username;
    JButton button = new JButton("Aggiungi Admin");

    public addadminView() {
        setLayout(new FlowLayout());
        setBackground(new Color(86, 214, 120));

        //Components
        username = new JTextField(10);
        add(new JLabel("Username dell'admin da aggiungere: "));
        add(username);
        add(button);


        //Listeners
        addadminViewListener listener = new addadminViewListener(username);
        button.addActionListener(listener);
    }
}
