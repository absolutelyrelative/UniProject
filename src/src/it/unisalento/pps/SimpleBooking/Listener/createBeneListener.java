package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.Tipo_BeneBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class createBeneListener implements ActionListener {
    JTextField nome_field;
    JTextField description_field;
    JTextField costo_pm_field;
    JTextField GPS_Lon_field;
    JTextField GPS_Lat_field;
    JTextField address_field;
    JComboBox<String> tipo_bene_list;
    JDatePickerImpl datePicker_inizio;
    JDatePickerImpl datePicker_fine;

    public createBeneListener(JTextField nome_field, JTextField description_field, JTextField costo_pm_field,
                              JTextField GPS_Lon_field, JTextField GPS_Lat_field,
                              JTextField address_field, JComboBox<String> tipo_bene_list,
                              JDatePickerImpl datePicker_inizio, JDatePickerImpl datePicker_fine) {

        this.nome_field = nome_field;
        this.description_field = description_field;
        this.costo_pm_field = costo_pm_field;
        this.GPS_Lon_field = GPS_Lon_field;
        this.GPS_Lat_field = GPS_Lat_field;
        this.address_field = address_field;
        this.tipo_bene_list = tipo_bene_list;
        this.datePicker_inizio = datePicker_inizio;
        this.datePicker_fine = datePicker_fine;

    }
    /*
    Result r = new Result();
            Utente u = SessionHelper.getInstance().getUser();

            if (u != null) {
                Amministratore a = UtenteDAO.getInstance().findIfUserIsAdmin(u.getUsername());
                if (a != null) {
                    Tipo_Bene tb = new Tipo_Bene();
                    tb.setNome(add.getText());
                    tb.setAmministratore_idAmministratore(a.getIdAmministratore());
                    r = Tipo_BeneDAO.getInstance().create(tb); //Tipo_Bene ha Primary Key(id, Nome), non ci dovrebbero essere conflitti.
                    if (r.isSuccess() == true) {
                        JOptionPane.showMessageDialog(null, "Tipo Bene aggiunto.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossibile aggiungere Tipo Bene.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Non è stato possibile trovare l'Amministratore associato all'Utente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Non è stato possibile trovare l'Utente dalla sessione.");
            }
     */

    //Tested, seems to work fine.
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Invia")) {
            Result r = new Result();
            Utente u = SessionHelper.getInstance().getUser();
            if (u != null) {
                //GET VENDITORE AND CONSTRUCT BENE
                Venditore v = UtenteDAO.getInstance().findIfUserIsVenditore(u.getUsername());

                if (v != null) {
                    Beni b = new Beni();
                    b.setNome(nome_field.getText());
                    b.setDescrizione(description_field.getText());
                    if (description_field.getText().length() > 255) {
                        JOptionPane.showMessageDialog(null, "Errore: Descrizione troppo lunga.");
                    } else {
                        //MySQL Date Syntax should be YYYY-MM-DD
                        Date Data_Inizio = new Date();
                        Date Data_Fine = new Date();
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Data_Inizio = format.parse(datePicker_inizio.getJFormattedTextField().getText());
                            Data_Fine = format.parse(datePicker_fine.getJFormattedTextField().getText());
                            if (Data_Inizio.after(Data_Fine)) {
                                JOptionPane.showMessageDialog(null, "Assicurati che le date siano corrette. Data Inizio non può venire dopo Data Fine.");
                            } else {
                                b.setData_Inizio(Data_Inizio);
                                b.setData_Fine(Data_Fine);
                                //Calculate costs
                                try {
                                    Float costo_pm = Float.parseFloat(costo_pm_field.getText());
                                    Float GPS1 = Float.parseFloat(GPS_Lat_field.getText());
                                    Float GPS2 = Float.parseFloat(GPS_Lon_field.getText());
                                    Float costo_pw = costo_pm / 4.35f;
                                    Float costo_pd = costo_pw / 7;
                                    b.setCosto_pm(costo_pm);
                                    b.setCosto_pw(costo_pw);
                                    b.setCosto_pd(costo_pd);
                                    b.setGPS_Lat(GPS1);
                                    b.setGPS_Lon(GPS2);
                                    b.setAddr(address_field.getText());
                                    b.setVenditore_idVenditore(v.getIdVenditore());
                                    //TODO: TEST!!
                                    String tipo = String.valueOf(tipo_bene_list.getSelectedItem());
                                    Tipo_Bene tb = Tipo_BeneBusiness.getInstance().getTipoFromName(tipo);
                                    if (tb != null) {
                                        b.setTipo_Bene_idTipo_Bene(tb.getIdTipo());
                                        b.setStato_Bene(0);
                                        b.setPubblicazione(0);
                                        //TODO: PLACEHOLDER, SET TO REAL ID LATER ON
                                        b.setAmministratore_idAmministratore(tb.getAmministratore_idAmministratore());
                                        Result c = new Result();
                                        c = BeniDAO.getInstance().create(b);
                                        if (c.isSuccess() == true) {
                                            JOptionPane.showMessageDialog(null, "Bene aggiunto. Attendi conferma da un Admin per la pubblicazione.");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Esiste già un bene con quel nome.");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Tipo Bene non trovato.");
                                    }
                                } catch (Exception h) {
                                    JOptionPane.showMessageDialog(null, "Errore durante conversione tipo. Controlla che i dati siano corretti.");
                                }
                            }

                        } catch (Exception g) {
                            //TODO: MAKE SURE DATA FINE > DATA INIZIO
                            JOptionPane.showMessageDialog(null, "Errore data. Controlla che sia corretta.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Venditore non trovato.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Utente non trovato. Effettua il Log-in.");
            }

        }
    }

}
