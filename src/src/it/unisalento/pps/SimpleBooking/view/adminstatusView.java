package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class adminstatusView extends JFrame {
    public JLabel status;

    public adminstatusView() {
        setLayout(new FlowLayout());
        status = new JLabel("SimpleBooking ADMIN");
        status.setForeground(Color.BLACK);
        add(status);
        setBackground(Color.green);
    }


    public JLabel returnStatusLabel() {
        return status;
    }

    public void setStatusLabel(String text) {
        status.setText(text);
    }

    public void setStatusLabelColour(Color c) {
        status.setForeground(c);
    }
}
