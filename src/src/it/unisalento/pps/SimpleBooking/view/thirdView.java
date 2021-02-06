package it.unisalento.pps.SimpleBooking.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class thirdView extends JPanel {
    JButton button = new JButton("Click me!");

    public thirdView() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You just clicked button");
            }
        });
        add(button);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 16));
        g.drawString("Hello there again!", 20, 20);
    }
}