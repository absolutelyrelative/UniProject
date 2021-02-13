package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.Listener.createBeneListener;
import it.unisalento.pps.SimpleBooking.Listener.modifyBeneListener;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

public class seller_modifyBeneView extends JFrame {
    private JLabel view_descr2 = new JLabel("Solo alcune modifiche possono essere effettuate.");
    private JLabel old_nome_label = new JLabel("Nome del bene da modificare:");
    private JTextField old_nome = new JTextField(10);
    private JLabel description_label = new JLabel("Descrizione:");
    private JTextField description_field = new JTextField(20);
    private JLabel costo_pd_label = new JLabel("Costo per giorno (€):");
    private JTextField costo_pd_field = new JTextField(4);
    private JLabel costo_pm_label = new JLabel("Costo per mese (€):");
    private JTextField costo_pm_field = new JTextField(8);
    private JLabel GPS_Lon_label = new JLabel("GPS Longitudine");
    private JTextField GPS_Lon_field = new JTextField(8);
    private JLabel GPS_Lat_label = new JLabel("GPS Latitudine:");
    private JTextField GPS_Lat_field = new JTextField(10);
    private JLabel address_label = new JLabel("Indirizzo:");
    private JTextField address_field = new JTextField(20);
    //TODO: ADD CUSTOM MENU TO SELECT TIPO BENE

    private JLabel data_inizio_label = new JLabel("Data Inizio:");
    private JDatePickerImpl datePicker_inizio;
    private JLabel data_fine_label = new JLabel("Data Fine:");
    private JDatePickerImpl datePicker_fine;
    private JButton confirmation = new JButton("Modifica");


    public seller_modifyBeneView() {
        JPanel upper_panel = new JPanel();
        JPanel lower_panel = new JPanel();
        JPanel containing_layout = new JPanel();
        //containing_layout.setLayout(new BoxLayout(containing_layout, BoxLayout.Y_AXIS));
        containing_layout.setLayout(new BorderLayout());
        upper_panel.setLayout(new FlowLayout());
        lower_panel.setLayout(new FlowLayout());
        JSeparator separator = new JSeparator();
        view_descr2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        upper_panel.add(view_descr2);
        upper_panel.add(separator);
        lower_panel.add(old_nome_label);
        lower_panel.add(old_nome);
        lower_panel.add(description_label);
        description_field.setPreferredSize(new Dimension(50, 20));
        lower_panel.add(description_field);
        lower_panel.add(costo_pm_label);
        lower_panel.add(costo_pm_field);
        lower_panel.add(GPS_Lon_label);
        lower_panel.add(GPS_Lon_field);
        lower_panel.add(GPS_Lat_label);
        lower_panel.add(GPS_Lat_field);
        lower_panel.add(address_label);
        lower_panel.add(address_field);

        //CALENDARIO
        //TODO: Cose stranissime per implementare JDatePicker che, per qualche motivo, non ha nessun tipo di documentazione.
        Properties p = new Properties();
        p.put("text.today", "Oggi");
        p.put("text.month", "Mese");
        p.put("text.year", "Anno");
        UtilDateModel model1 = new UtilDateModel();
        model1.setDate(2021, 1, 1);
        model1.setSelected(true);

        UtilDateModel model2 = new UtilDateModel();
        model2.setDate(2021, 1, 1);
        model2.setSelected(true);

        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);

        datePicker_inizio = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        datePicker_fine = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        //

        lower_panel.add(data_inizio_label);
        lower_panel.add(datePicker_inizio);
        lower_panel.add(data_fine_label);
        lower_panel.add(datePicker_fine);
        lower_panel.add(confirmation);
        containing_layout.add(upper_panel, BorderLayout.NORTH);
        containing_layout.add(lower_panel, BorderLayout.CENTER);
        getContentPane().add(containing_layout);


        //Listeners
        modifyBeneListener listener = new modifyBeneListener(old_nome, description_field, costo_pm_field,
                GPS_Lon_field, GPS_Lat_field, address_field, datePicker_inizio, datePicker_fine);
        confirmation.addActionListener(listener);
    }
}
