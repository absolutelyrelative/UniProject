package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.DAO.business.AmministratoreBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.VenditoreBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class adminView {
    JFrame frame;
    JTabbedPane tabbedPane;
    adminstatusView adminstatus = new adminstatusView();
    addadminView aaV = new addadminView();
    removeadminView raV = new removeadminView();
    adminTipoBeneView aTBV = new adminTipoBeneView();
    general_beniView gbV;

    public adminView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);
        ArrayList<Beni> b = AmministratoreBusiness.getInstance().beniToApprove();
        gbV = new general_beniView(b);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Aggiungi Admin",aaV.getContentPane());
        tabbedPane.addTab("Rimuovi Admin",raV.getContentPane());
        tabbedPane.addTab("Gestione Tipo Bene",aTBV.getContentPane());
        tabbedPane.addTab("Beni da approvare",gbV.getContentPane());

        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(adminstatus.getContentPane(), BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setSize(700,500);
        frame.validate();
        frame.doLayout();
        //frame.pack();
        frame.setVisible(true);

    }

}
