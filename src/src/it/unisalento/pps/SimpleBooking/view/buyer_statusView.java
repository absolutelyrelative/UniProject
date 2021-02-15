package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class buyer_statusView extends JFrame {
    public JLabel status;

    public buyer_statusView() {
        setLayout(new FlowLayout());
        status = new JLabel("SimpleBooking BUYER");
        status.setForeground(Color.CYAN);
        add(status);
        setBackground(Color.gray);
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
