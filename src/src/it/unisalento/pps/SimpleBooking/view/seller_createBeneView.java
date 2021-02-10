package it.unisalento.pps.SimpleBooking.view;


import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.Listener.addadminViewListener;
import it.unisalento.pps.SimpleBooking.Listener.createBeneListener;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
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
    private JLabel view_descr2 = new JLabel("Il bene sarà soggetto ad approvazione da amministratori.");
    private JLabel view_descr3 = new JLabel("Il costo mensile e annuale sarà calcolato.");
    private JLabel nome_label = new JLabel("Nome:");
    private JTextField nome_field = new JTextField(10);
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
    private JLabel tipo_bene_label = new JLabel("Tipo Bene:");
    private JComboBox<String> tipo_bene_list;

    private JLabel data_inizio_label = new JLabel("Data Inizio:");
    private JDatePickerImpl datePicker_inizio;
    private JLabel data_fine_label = new JLabel("Data Fine:");
    private JDatePickerImpl datePicker_fine;
    private JButton confirmation = new JButton("Invia");


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
        //lower_panel.add(costo_pd_label);
        //costo_pd_field.setValue(new Float(100.00));
        //lower_panel.add(costo_pd_field);
        lower_panel.add(costo_pm_label);
        lower_panel.add(costo_pm_field);
        lower_panel.add(GPS_Lon_label);
        lower_panel.add(GPS_Lon_field);
        lower_panel.add(GPS_Lat_label);
        lower_panel.add(GPS_Lat_field);
        lower_panel.add(address_label);
        lower_panel.add(address_field);
        lower_panel.add(tipo_bene_label);

        //JCOMBOBOX
        //Obtain ComboBox string items
        ArrayList<String> tipi = new ArrayList<String>();
        ArrayList<Tipo_Bene> tipi_bene = Tipo_BeneDAO.getInstance().findAll();
        for(Tipo_Bene t : tipi_bene){
            tipi.add(t.getNome());
        }
        String[] final_tipi = new String[ tipi.size() ];
        tipi.toArray( final_tipi );
        tipo_bene_list = new JComboBox<String>(final_tipi);
        lower_panel.add(tipo_bene_list);
        //

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
        createBeneListener listener = new createBeneListener(nome_field,description_field,costo_pm_field,
                GPS_Lon_field,GPS_Lat_field,address_field,tipo_bene_list,datePicker_inizio,datePicker_fine);
        confirmation.addActionListener(listener);
    }
}
