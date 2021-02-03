package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import java.awt.*;

public class loginstatusView extends JPanel {
    public JPanel c;
    public JLabel status;

    public loginstatusView() {
        c = new JPanel();
        c.setLayout(new FlowLayout());
        status = new JLabel("Test.");
        c.add(status);
        c.setBackground(Color.gray);
    }

    public JPanel returnPane() {
        return c;
    }

    public JLabel returnStatusLabel(){
        return status;
    }
    public void setStatusLabel(String text){
        status.setText(text);
    }
}
