package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.BeniBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.FeedbackBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.VenditoreBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.util.Comment;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class View {
    JFrame frame;
    JTabbedPane tabbedPane;
    loginstatusView loginstatus = new loginstatusView();
    loginView login = new loginView();
    registerView register = new registerView();
    credentialsresetView crV = new credentialsresetView();
    credentialschangeView ccV = new credentialschangeView();
    guest_beniView gbV;

    public View() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);
        ArrayList<Beni> b = BeniBusiness.getInstance().findAllPublished();
        gbV = new guest_beniView(b);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Log in", login.getContentPane());
        tabbedPane.addTab("Sign up", register);
        tabbedPane.addTab("Reset Credenziali", crV.getContentPane());
        tabbedPane.addTab("Cambio Credenziali", ccV.getContentPane());
        tabbedPane.addTab("Beni pubblicati", gbV.getContentPane());


        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(loginstatus, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setSize(800, 800);
        frame.validate();
        frame.doLayout();
        //frame.pack();
        frame.setVisible(true);

        //Ricalcola le liste
        tabbedPane.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Beni> b = BeniBusiness.getInstance().findAllPublished();
                gbV.recalculate(b);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

    }

    public static void main(String[] args) {
        new View();
    }

}
