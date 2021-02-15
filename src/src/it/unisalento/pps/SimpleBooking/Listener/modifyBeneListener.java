package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.CompratoreDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.OrdineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.BeniBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.Tipo_BeneBusiness;
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
            Result r = new Result();
            Utente u = SessionHelper.getInstance().getUser();
            if (u != null) {
                Venditore v = UtenteDAO.getInstance().findIfUserIsVenditore(u.getUsername());
                if (v != null) {
                    Beni b = BeniBusiness.getInstance().getBeneFromName(nome_field.getText());
                    if(b != null){
                        if(b.getVenditore_idVenditore() == v.getIdVenditore()){
                            Beni b_new = b;
                            if(description_field.getText().length() > 255){
                                JOptionPane.showMessageDialog(null, "Errore: Descrizione troppo lunga.");
                            }
                            else{
                                b_new.setDescrizione(description_field.getText());
                                try {
                                    Float costo_pm = Float.parseFloat(costo_pm_field.getText());
                                    Float costo_pw = costo_pm / 4.35f;
                                    Float costo_pd = costo_pw / 7;
                                    b_new.setCosto_pm(costo_pm);
                                    b_new.setCosto_pw(costo_pw);
                                    b_new.setCosto_pd(costo_pd);
                                    b_new.setAddr(address_field.getText());
                                    //MySQL Date Syntax should be YYYY-MM-DD
                                    Date Data_Inizio = new Date();
                                    Date Data_Fine = new Date();
                                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Data_Inizio = format.parse(datePicker_inizio.getJFormattedTextField().getText());
                                    Data_Fine = format.parse(datePicker_fine.getJFormattedTextField().getText());
                                    if (Data_Inizio.after(Data_Fine)) {
                                        JOptionPane.showMessageDialog(null, "Assicurati che le date siano corrette. Data Inizio non può venire dopo Data Fine.");
                                    } else {
                                        b_new.setData_Inizio(Data_Inizio);
                                        b_new.setData_Fine(Data_Fine);
                                        BeniDAO.getInstance().updateBene(b,b_new);
                                        JOptionPane.showMessageDialog(null, "Bene modificato correttamente.");
                                        //TODO: INFORM BUYERS OF THIS BENE IF THEY EXIST
                                        Ordine o = OrdineDAO.getInstance().getOrdineFromBeni(b_new.getIdBeni());
                                        if(o != null){
                                            Compratore c = CompratoreDAO.getInstance().findById(o.getCompratore_idCompratore());
                                            if(c != null){
                                                Utente u_c = UtenteDAO.getInstance().findById(c.getId());
                                                if(u_c != null){
                                                    new MailHelper().send(u_c.getEmail(), "SimpleBooking: Ordine CAMBIATO", "Ciao. Il bene " + b_new.getNome() + " è stato CAMBIATO. Controlla e/o cancella l'ordine se necessario.");
                                                }
                                                else{
                                                    //Non dovrebbe accadere. TODO: ADD CHECKS
                                                }
                                            }
                                            else{
                                                //Non dovrebbe accadere. TODO: ADD CHECKS
                                            }
                                        }
                                        else{ //No order exists, no need to do anything
                                            return;
                                        }
                                    }
                                }
                                catch(Exception h){
                                    JOptionPane.showMessageDialog(null, "Errore durante conversione tipo. Controlla che i dati siano corretti.");
                                }
                            }

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Errore: Il bene non appartiene a te.");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Errore: Bene non trovato. Controlla il nome.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Errore: Venditore non trovato.");
                }
            }
        }
    }
}
