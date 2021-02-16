package it.unisalento.pps.SimpleBooking.view;


import it.unisalento.pps.SimpleBooking.Listener.loginViewListener;

import javax.swing.*;
import java.awt.*;

public class loginView extends JFrame {
    //Non è corretto prendere il parent object di un child object in questo modo
    //ma lo è stato fatto temporaneamente
    //JPanel Card = this;
    JTextField lg_username;
    JPasswordField lg_password;
    JButton lg_button = new JButton("Log in");
    JLabel result;
    JButton Seller_View = new JButton("Schermata Venditori");
    JButton Admin_View = new JButton("Schermata Admin");
    JButton Buyer_View = new JButton("Schermata Compratori");

    public loginView() {
        JPanel upper_panel = new JPanel();
        JPanel center_panel = new JPanel();
        JPanel south_panel = new JPanel();
        JPanel containing_layout = new JPanel();
        containing_layout.setLayout(new BorderLayout());
        upper_panel.setLayout(new FlowLayout());
        center_panel.setLayout(new FlowLayout());
        south_panel.setLayout(new FlowLayout());

        setLayout(new FlowLayout());
        setBackground(new Color(200, 221, 242));

        //Components
        result = new JLabel("");
        lg_username = new JTextField(10);
        lg_password = new JPasswordField(10);
        upper_panel.add(new JLabel("Username: "));
        upper_panel.add(lg_username);
        upper_panel.add(new JLabel("Password: "));
        upper_panel.add(lg_password);
        upper_panel.add(lg_button);
        center_panel.add(result);
        Seller_View.setVisible(false);
        south_panel.add(Seller_View);
        Admin_View.setVisible(false);
        south_panel.add(Admin_View);
        Buyer_View.setVisible(false);
        south_panel.add(Buyer_View);

        containing_layout.add(upper_panel, BorderLayout.NORTH);
        containing_layout.add(center_panel, BorderLayout.CENTER);
        containing_layout.add(south_panel, BorderLayout.SOUTH);
        getContentPane().add(containing_layout);


        //Listeners
        loginViewListener listener = new loginViewListener(lg_username, lg_password, result, Seller_View, Admin_View, Buyer_View);
        lg_button.addActionListener(listener);
    }


}
