package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.TipoBeneListener;
import it.unisalento.pps.SimpleBooking.Listener.addadminViewListener;

import javax.swing.*;
import static java.awt.GridBagConstraints.*;
import java.awt.*;

public class adminTipoBeneView extends JFrame {
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JLabel description = new JLabel("Gestione Tipo Bene");
    JTextField add_name = new JTextField(10);
    JButton add_button = new JButton("Aggiungi Tipo Bene");
    JLabel add = new JLabel("Nomenclatura tipo bene da aggiungere: ");
    JTextField rmv_name = new JTextField(10);
    JButton rmv_button = new JButton("Rimuovi Tipo Bene");
    JLabel rmv = new JLabel("Nomenclatura tipo bene da rimuovere: ");

    public adminTipoBeneView() {
        setLayout(new BorderLayout());
        setForeground(new Color(86, 214, 120));
        JPanel center = new JPanel();
        JPanel north = new JPanel();
        description.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));

        north.add(description);
        center.add(add);
        center.add(add_name);
        center.add(add_button);

        center.add(rmv);
        center.add(rmv_name);
        center.add(rmv_button);


        getContentPane().add(north,BorderLayout.NORTH);
        getContentPane().add(center,BorderLayout.CENTER);


        //Listeners
        TipoBeneListener listener = new TipoBeneListener(add_name,rmv_name);
        add_button.addActionListener(listener);
        rmv_button.addActionListener(listener);

    }
}