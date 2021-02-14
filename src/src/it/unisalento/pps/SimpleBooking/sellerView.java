package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.business.VenditoreBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class sellerView {
    JFrame frame;
    JTabbedPane tabbedPane;
    sellerstatusView ssV = new sellerstatusView();
    seller_createBeneView scBV = new seller_createBeneView();
    seller_addImagesView saIV = new seller_addImagesView();
    seller_beniView gbV;
    seller_modifyBeneView smBV = new seller_modifyBeneView();

    public sellerView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);
        ArrayList<Beni> b = VenditoreBusiness.getInstance().findOwnBeni(SessionHelper.getInstance().getUser().getUsername());
        gbV = new seller_beniView(b);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Crea Bene", scBV.getContentPane());
        tabbedPane.addTab("Aggiungi Immagini",saIV.getContentPane());
        tabbedPane.addTab("Tuoi beni", gbV.getContentPane());
        tabbedPane.addTab("Modifica beni",smBV.getContentPane());



        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(ssV.getContentPane(), BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setSize(800,800);
        frame.validate();
        frame.doLayout();
        //frame.pack();
        frame.setVisible(true);

        //Ricalcola le liste
        tabbedPane.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Beni> b = VenditoreBusiness.getInstance().findOwnBeni(SessionHelper.getInstance().getUser().getUsername());
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
}
