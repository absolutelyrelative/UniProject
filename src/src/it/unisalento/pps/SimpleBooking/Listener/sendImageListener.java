package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.ImmagineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.BeniBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.ImmagineBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.VenditoreBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;
import it.unisalento.pps.SimpleBooking.Model.Utente;
import it.unisalento.pps.SimpleBooking.Model.Venditore;
import it.unisalento.pps.SimpleBooking.util.Result;
import it.unisalento.pps.SimpleBooking.util.SessionHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class sendImageListener implements ActionListener {
    private JTextField bene;
    private DefaultListModel model;
    private ArrayList<String> immagini;

    public sendImageListener(JTextField bene, DefaultListModel model) {
        this.bene = bene;
        this.model = model;
    }


    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand()).equals("Invia immagini")) {
            //Check 1 - Controlla che il bene esista
            Beni beneFromName = BeniBusiness.getInstance().getBeneFromName(bene.getText());
            if (beneFromName != null) {
                Utente utente = SessionHelper.getInstance().getUser();
                if (utente != null) {
                    Venditore userIsVenditore = VenditoreBusiness.getInstance().findifUserIsVenditore(utente.getUsername());
                    if (userIsVenditore != null) {
                        if (userIsVenditore.getIdVenditore() == beneFromName.getVenditore_idVenditore()) {
                            immagini = new ArrayList<String>();
                            for (int ctr = 0; ctr < model.getSize(); ctr++) {
                                immagini.add((String) model.getElementAt(ctr)); //TODO: CHECK CAST
                            }
                            for (String path : immagini) {
                                Result result = this.sendImage(path, beneFromName.getIdBeni());
                                if (!result.isSuccess()) {
                                    JOptionPane.showMessageDialog(null, "Immagine troppo grande o rimossa.");
                                } else {
                                    JOptionPane.showMessageDialog(null, model.getSize() + " immagini aggiunte al bene.");
                                }
                                model.clear();

                            }
                            immagini.clear(); //THIS THREW AN EXCEPTION IN THE FOR LOOP SO I HAD TO MOVE IT.
                        } else {
                            JOptionPane.showMessageDialog(null, "Il bene non appartiene a te.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Non sei venditore.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Effettua il Log-in.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bene non trovato. Assicurati che il nome sia corretto.");
            }
        }
        if ((e.getActionCommand()).equals("Rimuovi tutte le immagini")) {
            //Check 1 - Controlla che il bene esista
            Beni beneFromName = BeniBusiness.getInstance().getBeneFromName(bene.getText());
            if (beneFromName != null) {
                Utente utente = SessionHelper.getInstance().getUser();
                if (utente != null) {
                    Venditore userIsVenditore = VenditoreBusiness.getInstance().findifUserIsVenditore(utente.getUsername());
                    if (userIsVenditore != null) {
                        if (userIsVenditore.getIdVenditore() == beneFromName.getVenditore_idVenditore()) {
                            Result result = ImmagineBusiness.getInstance().removeImmaginiFromBene(beneFromName);
                            if (result.isSuccess()) {
                                JOptionPane.showMessageDialog(null, "Immagini rimosse.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Non tutte le immagini sono state rimosse.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Il bene non appartiene a te.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Non sei venditore.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Effettua il Log-in.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bene non trovato. Assicurati che il nome sia corretto.");
            }
        }
    }

    private Result sendImage(String path, int beni_id) {
        Result result1 = new Result();
        Immagine immagine = new Immagine();

        try {
            //File immagine = new File(path);
            immagine.setBeni_idBeni(beni_id);
            File file = new File(path);
            Result result = ImmagineDAO.getInstance().create(immagine, file);
            if (result.isSuccess()) {
                result1.setSuccess(true);
                result1.setMessage("Immagine aggiunta.");
                return result1;
            } else {
                result1.setSuccess(false);
                result1.setMessage("Query non eseguita. Immagine non aggiunta.");
                return result1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        result1.setSuccess(false);
        result1.setMessage("Routine not completed.");
        return result1;
    }

}
