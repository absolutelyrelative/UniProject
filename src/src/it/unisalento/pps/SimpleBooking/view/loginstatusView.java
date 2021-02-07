package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class loginstatusView extends JPanel {
    public JLabel status;

    public loginstatusView() {
        setLayout(new FlowLayout());
        status = new JLabel("Test.");
        status.setForeground(Color.BLACK);
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
