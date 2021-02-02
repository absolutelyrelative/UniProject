package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.view.loginView;
import it.unisalento.pps.SimpleBooking.view.loginstatusView;
import it.unisalento.pps.SimpleBooking.view.registerView;

import javax.swing.*;
import java.awt.*;

public class View {
    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("TabDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //COMPONENTI
        loginstatusView loginstatus = new loginstatusView();

        //COMPONENTI DI JTabbedPane
        loginView login = new loginView();
        registerView register = new registerView();
        tabbedPane.addTab("Log in", login.returnPane());
        tabbedPane.addTab("Register", register.returnPane());
        //


        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(loginstatus.returnPane(),BorderLayout.SOUTH);

        //frame.setMinimumSize(new Dimension(100,100));
        frame.pack();
        frame.setVisible(true);

    }
}
