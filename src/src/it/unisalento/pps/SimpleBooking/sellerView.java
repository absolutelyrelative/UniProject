package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.view.*;

import javax.swing.*;
import java.awt.*;

public class sellerView {
    JFrame frame;
    JTabbedPane tabbedPane;
    sellerstatusView ssV = new sellerstatusView();
    thirdView tV = new thirdView();
    seller_createBeneView scBV = new seller_createBeneView();

    public sellerView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("SimpleBooking");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //COMPONENTI DI JTabbedPane
        tabbedPane.addTab("Third Panel",tV);
        tabbedPane.addTab("Crea Bene", scBV.getContentPane());



        //AGGIUNGI TABBEDPANE->PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //AGGIUNGI ALTRI PANE IN FRAME->CONTENTPANE
        frame.getContentPane().add(ssV.getContentPane(), BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setSize(700,500);
        frame.validate();
        frame.doLayout();
        //frame.pack();
        frame.setVisible(true);

    }
}
