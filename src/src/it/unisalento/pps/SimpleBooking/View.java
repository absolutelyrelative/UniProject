package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.ImmagineDAO;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;
import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class View {
    JFrame frame;
    JTabbedPane tabbedPane;
    loginstatusView loginstatus = new loginstatusView();
    loginView login = new loginView();
    registerView register = new registerView();
    credentialsresetView crV = new credentialsresetView();
    credentialschangeView ccV = new credentialschangeView();

    public View() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Log in", login);
        tabbedPane.addTab("Sign up", register);
        tabbedPane.addTab("Reset Credenziali",crV.getContentPane());
        tabbedPane.addTab("Cambio Credenziali",ccV.getContentPane());


        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(loginstatus, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setSize(800,600);
        frame.validate();
        frame.doLayout();
        //frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        /*Immagine t = new Immagine();
        t.setBeni_idBeni(1);
        File f = new File("C:\\Users\\Paolo Unisalento\\Desktop\\Untitled.png");
        ImmagineDAO.getInstance().create(t,f);*/
        ArrayList<Beni> b = BeniDAO.getInstance().findAll();
        new buyer_beniView(b);

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
