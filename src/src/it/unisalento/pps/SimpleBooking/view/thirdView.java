package it.unisalento.pps.SimpleBooking.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class thirdView extends JPanel {
    JButton button = new JButton("Click me!");

    public thirdView() {
        this.setBackground(new Color(200, 221, 242));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You just clicked button");
            }
        });
        add(button);
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(84, 128, 125));
        g.setFont(new Font("Verdana", Font.BOLD, 16));

        Dimension size = this.getSize();
        double height = size.getHeight();   //Ritorna sempre 0,0, nonostanate la chiamata a validate() & doLayout()
        double width = size.getWidth();     //Stackoverflow mi dice che è un problema riguardante stime di swing.
        int draw_height = (int) (height + 300);
        int draw_width = (int) (width + 500);
        g.drawString("SimpleBooking", draw_width, draw_height);
    }
}