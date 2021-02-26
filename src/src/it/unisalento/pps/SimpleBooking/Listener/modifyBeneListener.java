package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.CompratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.*;
import it.unisalento.pps.SimpleBooking.Model.*;
import it.unisalento.pps.SimpleBooking.util.MailHelper;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class modifyBeneListener implements ActionListener {
    JTextField nome_field;
    JTextField description_field;
    JTextField costo_pm_field;
    JTextField GPS_Lon_field;
    JTextField GPS_Lat_field;
    JTextField address_field;
    JDatePickerImpl datePicker_inizio;
    JDatePickerImpl datePicker_fine;

    public modifyBeneListener(JTextField nome_field, JTextField description_field, JTextField costo_pm_field,
                              JTextField GPS_Lon_field, JTextField GPS_Lat_field,
                              JTextField address_field,
                              JDatePickerImpl datePicker_inizio, JDatePickerImpl datePicker_fine) {

        this.nome_field = nome_field;
        this.description_field = description_field;
        this.costo_pm_field = costo_pm_field;
        this.GPS_Lon_field = GPS_Lon_field;
        this.GPS_Lat_field = GPS_Lat_field;
        this.address_field = address_field;
        this.datePicker_inizio = datePicker_inizio;
        this.datePicker_fine = datePicker_fine;

    }


    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Modifica")) {
            Result result = new Result();
            Utente utente = SessionHelper.getInstance().getUser();
            if (utente != null) {
                Venditore venditore = VenditoreBusiness.getInstance().findifUserIsVenditore(utente.getUsername());
                if (venditore != null) {
                    Beni beneFromName = BeniBusiness.getInstance().getBeneFromName(nome_field.getText());
                    if (beneFromName != null) {
                        if (beneFromName.getVenditore_idVenditore() == venditore.getIdVenditore()) {
                            if (description_field.getText().length() > 255) {
                                JOptionPane.showMessageDialog(null, "Errore: Descrizione troppo lunga.");
                            } else {
                                beneFromName.setDescrizione(description_field.getText());
                                try {
                                    Float costo_pm = Float.parseFloat(costo_pm_field.getText());
                                    Float costo_pw = costo_pm / 4.35f;
                                    Float costo_pd = costo_pw / 7;
                                    beneFromName.setCosto_pm(costo_pm);
                                    beneFromName.setCosto_pw(costo_pw);
                                    beneFromName.setCosto_pd(costo_pd);
                                    beneFromName.setAddr(address_field.getText());
                                    //MySQL Date Syntax should be YYYY-MM-DD
                                    Date Data_Inizio = new Date();
                                    Date Data_Fine = new Date();
                                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Data_Inizio = format.parse(datePicker_inizio.getJFormattedTextField().getText());
                                    Data_Fine = format.parse(datePicker_fine.getJFormattedTextField().getText());
                                    if (Data_Inizio.after(Data_Fine)) {
                                        JOptionPane.showMessageDialog(null, "Assicurati che le date siano corrette. Data Inizio non può venire dopo Data Fine.");
                                    } else {
                                        beneFromName.setData_Inizio(Data_Inizio);
                                        beneFromName.setData_Fine(Data_Fine);
                                        BeniBusiness.getInstance().updateBene(beneFromName, beneFromName);
                                        JOptionPane.showMessageDialog(null, "Bene modificato correttamente.");
                                        //TODO: INFORM BUYERS OF THIS BENE IF THEY EXIST
                                        Ordine orderFromBeniID = OrdineBusiness.getInstance().getOrderFromBeniID(beneFromName.getIdBeni());
                                        if (orderFromBeniID != null) {
                                            Compratore compratore = CompratoreBusiness.getInstance().findById(orderFromBeniID.getCompratore_idCompratore());
                                            if (compratore != null) {
                                                Utente utenteCompratore = UtenteBusiness.getInstance().findById(compratore.getId());
                                                if (utenteCompratore != null) {
                                                    new MailHelper().send(utenteCompratore.getEmail(), "SimpleBooking: Ordine CAMBIATO", "Ciao. Il bene " + beneFromName.getNome() + " è stato CAMBIATO. Controlla e/o cancella l'ordine se necessario.");
                                                } else {
                                                    //Non dovrebbe accadere. TODO: ADD CHECKS
                                                }
                                            } else {
                                                //Non dovrebbe accadere. TODO: ADD CHECKS
                                            }
                                        } else { //No order exists, no need to do anything
                                            return;
                                        }
                                    }
                                } catch (Exception h) {
                                    JOptionPane.showMessageDialog(null, "Errore durante conversione tipo. Controlla che i dati siano corretti.");
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Errore: Il bene non appartiene a te.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore: Bene non trovato. Controlla il nome.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Venditore non trovato.");
                }
            }
        }
    }
}
