package it.unisalento.pps.SimpleBooking;

import it.unisalento.pps.SimpleBooking.view.PanelOne;
import it.unisalento.pps.SimpleBooking.view.PanelTwo;

import javax.swing.*;
import java.awt.*;

public class View {
    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("TabDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();

        PanelOne a = new PanelOne();
        PanelTwo b = new PanelTwo();
        tabbedPane.addTab("test",a.returnPane());
        tabbedPane.addTab("test2",b);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
