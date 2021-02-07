package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.business.UtenteBusiness;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.swing.*;
import java.awt.*;

public class loginstatusView extends JPanel {
    public JLabel status;

    public loginstatusView() {
        setLayout(new FlowLayout());
        //Deprecato, utilizzo pagina del Log-in per verificare status
        /*if(SessionHelper.getInstance().getSession()==true){
            status = new JLabel("Benvenuto " + SessionHelper.getInstance().getUser().getUsername());
        }
        else{
            status = new JLabel("Guest");
        }*/
        status = new JLabel("SimpleBooking");
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
