package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.view.loginView;
import it.unisalento.pps.SimpleBooking.view.loginstatusView;
import it.unisalento.pps.SimpleBooking.view.registerView;

import javax.swing.*;
import java.awt.*;

public class View {
    JFrame frame;
    JTabbedPane tabbedPane;
    loginstatusView loginstatus;
    loginView login;
    registerView register;

    public View() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("TabDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //COMPONENTI
        loginstatus = new loginstatusView();

        //COMPONENTI DI JTabbedPane
        login = new loginView();
        register = new registerView();
        tabbedPane.addTab("Log in", login.returnPane());
        tabbedPane.addTab("Register", register.returnPane());
        //


        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(loginstatus.returnPane(), BorderLayout.SOUTH);

        //frame.setMinimumSize(new Dimension(100,100));
        frame.pack();
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public loginstatusView getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(loginstatusView loginstatus) {
        this.loginstatus = loginstatus;
    }

    public loginView getLogin() {
        return login;
    }

    public void setLogin(loginView login) {
        this.login = login;
    }

    public registerView getRegister() {
        return register;
    }

    public void setRegister(registerView register) {
        this.register = register;
    }

}
