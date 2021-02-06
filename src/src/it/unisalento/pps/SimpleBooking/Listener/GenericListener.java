package it.unisalento.pps.SimpleBooking.Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GenericListener implements ActionListener {
    JTextField tf;

    GenericListener(JTextField tf) {
        this.tf = tf;
    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Button1"))
            tf.setText("Button1 clicked");
        if ((e.getActionCommand()).equals("Button2"))
            tf.setText("Button2 clicked");
        if ((e.getActionCommand()).equals("Button3"))
            tf.setText("Button3 clicked");
    }
}

//USAGE:
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionDemo extends JFrame
{
    public ActionDemo()
    {
        setLayout(new FlowLayout());

        // Add buttons to the frame
        JButton b1=new JButton("Button1");
        JButton b2=new JButton("Button2");
        JButton b3=new JButton("Button3");
        JTextField tf=new JTextField(10);

        AListener listen=new  AListener(tf);
        b1.addActionListener(listen);
        b2.addActionListener(listen);
        b3.addActionListener(listen);

        add(b1);add(b2);add(b3);add(tf);
  }

  public static void main(String[] args)
  {
      ActionDemo frame = new ActionDemo();
      frame.setTitle("Action Demo");
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 100);
      frame.setVisible(true);
  }
}
 */