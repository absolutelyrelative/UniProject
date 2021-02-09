package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class sellerstatusView extends JFrame {
    public JLabel status;

    public sellerstatusView() {
        setLayout(new FlowLayout());
        status = new JLabel("SimpleBooking Seller");
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
