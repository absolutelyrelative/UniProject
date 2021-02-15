package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.business.BeniBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.VenditoreBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class buyerView {
    JFrame frame;
    JTabbedPane tabbedPane;
    buyer_statusView bsV = new buyer_statusView();
    buyer_beniView bbV;

    public buyerView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking BUYER");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);
        ArrayList<Beni> b = BeniBusiness.getInstance().findAllPublished();
        bbV = new buyer_beniView(b);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Prenota Beni", bbV.getContentPane());


        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(bsV.getContentPane(), BorderLayout.SOUTH);

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
                bbV.recalculate(b);
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
