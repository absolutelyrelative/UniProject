package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class loginstatusView extends JPanel {
    public JPanel c;
    public JLabel status = new JLabel("test");

    public loginstatusView(){
        c = new JPanel();
        c.setLayout(new FlowLayout());
        c.add(status);
        c.setBackground(Color.gray);
    }

    public JPanel returnPane(){
        return c;
    }
}
