package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.VenditoreBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class adminView {
    JFrame frame;
    JTabbedPane tabbedPane;
    adminstatusView adminstatus = new adminstatusView();
    addadminView aaV = new addadminView();
    removeadminView raV = new removeadminView();
    adminTipoBeneView aTBV = new adminTipoBeneView();
    admin_beniView abV;
    seller_removeOrder srO = new seller_removeOrder();

    public adminView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking ADMIN");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);
        ArrayList<Beni> beniToApprove = AmministratoreBusiness.getInstance().beniToApprove();
        abV = new admin_beniView(beniToApprove);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Aggiungi Admin", aaV.getContentPane());
        tabbedPane.addTab("Rimuovi Admin", raV.getContentPane());
        tabbedPane.addTab("Gestione Tipo Bene", aTBV.getContentPane());
        tabbedPane.addTab("Beni da approvare", abV.getContentPane());
        tabbedPane.addTab("Rimuovi Ordini", srO.getContentPane());

        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(adminstatus.getContentPane(), BorderLayout.SOUTH);

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
                ArrayList<Beni> beniToApprove1 = AmministratoreBusiness.getInstance().beniToApprove();
                abV.recalculate(beniToApprove1);
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
