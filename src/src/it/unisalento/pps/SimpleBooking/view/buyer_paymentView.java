package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.business.PagamentoBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Pagamento;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

public class buyer_paymentView extends JFrame {
    private Beni beni;
    private Pagamento pagamento;

    private JLabel pay_label = new JLabel("Effettua il pagamento e aspetta il resoconto.");
    private JLabel importo_label = new JLabel("Importo (€): ");
    private JTextField importo = new JTextField(10);
    private JLabel card_number = new JLabel("Numero carta:");
    private JFormattedTextField card_field = new JFormattedTextField();
    private JLabel cvv = new JLabel("CVV:");
    private JFormattedTextField cvv_field = new JFormattedTextField();
    private JLabel pin = new JLabel("PIN:");
    private JFormattedTextField pin_field = new JFormattedTextField();
    private JButton pay = new JButton("Paga");
    private JLabel esito = new JLabel();
    private String costo;

    public buyer_paymentView(Beni beni, Pagamento pagamento) {
        this.beni = beni;
        this.pagamento = pagamento;

        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel south_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());

        //Aggiungi formattazione per i nostri valori
        try {
            DecimalFormat costo_Format = new DecimalFormat("0.00");
            MaskFormatter card_Format = new MaskFormatter("####-####-####-####");
            MaskFormatter cvv_Format = new MaskFormatter("###");
            MaskFormatter pin_Format = new MaskFormatter("####");
            costo = costo_Format.format(pagamento.getImporto());
            importo.setText(costo);
            card_Format.install(card_field);
            cvv_Format.install(cvv_field);
            pin_Format.install(pin_field);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        card_field.setColumns(16);
        cvv_field.setColumns(3);
        pin_field.setColumns(4);
        importo.setEditable(false);
        north_panel.add(pay_label);
        center_panel.add(importo_label);
        center_panel.add(importo);
        center_panel.add(card_number);
        center_panel.add(card_field);
        center_panel.add(cvv);
        center_panel.add(cvv_field);
        center_panel.add(pin);
        center_panel.add(pin_field);
        center_panel.add(pay);
        south_panel.add(esito);
        setSize(600, 200);
        setResizable(false);
        setForeground(Color.CYAN);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        container_panel.add(north_panel, BorderLayout.NORTH);
        container_panel.add(center_panel, BorderLayout.CENTER);
        container_panel.add(south_panel, BorderLayout.SOUTH);
        getContentPane().add(container_panel);

        pay.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            if (pagamento.getStato() != 1) {
                Result result = PagamentoBusiness.getInstance().pay(this.pagamento, card_field.getText(), cvv_field.getText(), pin_field.getText());
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(null, "Pagamento effettuato correttamente.");
                    pagamento.setStato(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Errore durante la procedura di pagamento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Il bene è già stato pagato.");
            }
        }
    }
}
