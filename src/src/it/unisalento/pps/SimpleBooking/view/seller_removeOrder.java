package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.BeniBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.OrdineBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.PagamentoBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Ordine;
import it.unisalento.pps.SimpleBooking.Model.Pagamento;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

public class seller_removeOrder extends JFrame {

    private JLabel descr = new JLabel("Rimuovi ordini per beni");
    private JLabel beni_name = new JLabel("Nome bene:");
    private JTextField beni_field = new JTextField(30);
    private JButton rimuovi = new JButton("Rimuovi");


    public seller_removeOrder() {
        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel south_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());


        north_panel.add(descr);
        center_panel.add(beni_name);
        center_panel.add(beni_field);
        center_panel.add(rimuovi);
        container_panel.add(north_panel, BorderLayout.NORTH);
        container_panel.add(center_panel, BorderLayout.CENTER);
        container_panel.add(south_panel, BorderLayout.SOUTH);
        getContentPane().add(container_panel);

        rimuovi.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rimuovi) {
            Beni b = BeniBusiness.getInstance().getBeneFromName(beni_field.getText());
            if (b != null) {
                Ordine o = OrdineBusiness.getInstance().getOrderFromBeniID(b.getIdBeni());
                if (o != null) {
                    Result r = OrdineDAO.getInstance().delete(o);
                    if (r.isSuccess()) {
                        JOptionPane.showMessageDialog(null, "Ordine & Pagamenti eliminati");
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossibile rimuovere Ordine.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Non esiste ordine per questo bene.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bene non trovato. Assicurati che il nome sia corretto.");
            }
        }

    }
}
