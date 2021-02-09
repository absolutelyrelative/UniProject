package it.unisalento.pps.SimpleBooking.view;


import it.unisalento.pps.SimpleBooking.util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Properties;

public class seller_createBeneView extends JFrame {
    /* setLayout(new BorderLayout(10, 10));
         setForeground(new Color(86, 214, 120));
         JPanel center = new JPanel();
         JPanel north = new JPanel();
         JPanel south = new JPanel();
         JPanel west = new JPanel();
         reset_description.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

         north.add(reset_description);
         center.add(username_description);
         center.add(reset_username);
         center.add(reset_button);


         getContentPane().add(north, BorderLayout.NORTH);
         getContentPane().add(center, BorderLayout.CENTER);


         //Listeners
         credentialsresetViewListener listener = new credentialsresetViewListener(reset_username);
         reset_button.addActionListener(listener);*/
    JLabel view_descr2 = new JLabel("Il bene sarà soggetto ad approvazione da amministratori.");
    JLabel view_descr3 = new JLabel("Il costo mensile e annuale sarà calcolato.");
    JLabel nome_label = new JLabel("Nome:");
    JTextField nome_field = new JTextField(10);
    JLabel description_label = new JLabel("Descrizione:");
    JTextField description_field = new JTextField(20);
    JLabel costo_pd_label = new JLabel("Costo per giorno (€):");
    JFormattedTextField costo_pd_field = new JFormattedTextField(8);
    JLabel GPS_Lon_label = new JLabel("GPS Longitudine");
    JFormattedTextField GPS_Lon_field = new JFormattedTextField(10);
    JLabel GPS_Lat_label = new JLabel("GPS Latitudine:");
    JFormattedTextField GPS_Lat_field = new JFormattedTextField(10);
    JLabel address_label = new JLabel("Indirizzo:");
    JTextField address_field = new JTextField(20);
    //TODO: ADD CUSTOM MENU TO SELECT TIPO BENE
    JLabel tipo_bene_label = new JLabel("Tipo Bene:");

    JLabel data_inizio_label = new JLabel("Data Inizio:");
    private JDatePickerImpl datePicker_inizio;
    JLabel data_fine_label = new JLabel("Data Fine:");
    private JDatePickerImpl datePicker_fine;


    public seller_createBeneView() {
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
        lower_panel.add(nome_label);
        lower_panel.add(nome_field);
        lower_panel.add(description_label);
        description_field.setPreferredSize(new Dimension(50, 20));
        lower_panel.add(description_field);
        lower_panel.add(costo_pd_label);
        costo_pd_field.setValue(new Float(100.00));
        lower_panel.add(costo_pd_field);
        lower_panel.add(GPS_Lon_label);
        GPS_Lon_field.setValue(new Double(1000000000));
        lower_panel.add(GPS_Lon_field);
        lower_panel.add(GPS_Lat_label);
        GPS_Lat_field.setValue(new Double(1000000000));
        lower_panel.add(GPS_Lat_field);
        lower_panel.add(address_label);
        lower_panel.add(address_field);
        lower_panel.add(tipo_bene_label);

        //TODO: Cose stranissime per implementare JDatePicker che, per qualche motivo, non ha nessun tipo di documentazione.
        Properties p = new Properties();
        p.put("text.today", "Oggi");
        p.put("text.month", "Mese");
        p.put("text.year", "Anno");
        UtilDateModel model = new UtilDateModel();
        model.setDate(1990, 8, 24);
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker_inizio = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker_fine = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        lower_panel.add(data_inizio_label);
        lower_panel.add(datePicker_inizio);
        lower_panel.add(data_fine_label);
        lower_panel.add(datePicker_fine);
        containing_layout.add(upper_panel,BorderLayout.NORTH);
        containing_layout.add(lower_panel,BorderLayout.CENTER);
        getContentPane().add(containing_layout);

    }
}
