package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.view.loginView;
import it.unisalento.pps.SimpleBooking.view.loginstatusView;
import it.unisalento.pps.SimpleBooking.view.registerView;
import it.unisalento.pps.SimpleBooking.view.thirdView;

import javax.swing.*;
import java.awt.*;

public class View {
    JFrame frame;
    JTabbedPane tabbedPane;
    loginstatusView loginstatus = new loginstatusView();
    loginView login = new loginView();
    registerView register = new registerView();
    thirdView tV = new thirdView();

    public View() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Log in", login.returnPane());
        tabbedPane.addTab("Register", register.returnPane());
        tabbedPane.addTab("Third Panel",tV);


        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(loginstatus.returnPane(), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new View();
        /*Utente u = UtenteDAO.getInstance().findById(2);
        Session.getInstance().saveSession(u);
        System.out.println("isActive: " + Session.getInstance().isActive());
        System.out.println("email: "+Session.getInstance().getUser().getEmail());
        System.out.println("id: "+Session.getInstance().getUser().getId());*/
        //Session.getInstance().closeSession();
        //System.out.println("isActive: " + SessionHelper.getInstance().isActive());
        //new MailHelper().send("paolo.danese@studenti.unisalento.it", "oggetto", "msg di test");
        //Result r = UtenteBusiness.getInstance().login("","");
        //System.out.println(r.isSuccess() + r.getMessage() + r.getType());
    }

}
