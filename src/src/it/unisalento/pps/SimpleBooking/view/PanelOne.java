package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class PanelOne extends JPanel {
    public JPanel c;
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    public PanelOne(){
        c = new JPanel();
        c.add(new JButton("AAAAA"));
        c.add(new JLabel("aAaaAaaAA"));
    }

    public JPanel returnPane(){
        return c;
    }
}
