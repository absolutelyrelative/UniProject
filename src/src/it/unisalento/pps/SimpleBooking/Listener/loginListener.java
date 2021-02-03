package it.unisalento.pps.SimpleBooking.Listener;

import java.awt.event.*;

import it.unisalento.pps.SimpleBooking.View;
import it.unisalento.pps.SimpleBooking.view.*;

public class loginListener implements ActionListener {

    public final static String login_cmd = "login";


    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        if (com.equals(login_cmd)) {
            login();
        } else {

        }
    }

    private void login() {
        View v = new View();
        v.getLoginstatus().setStatusLabel("Pressed.");
    }

}
