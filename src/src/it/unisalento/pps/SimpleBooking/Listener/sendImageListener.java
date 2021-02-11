package it.unisalento.pps.SimpleBooking.Listener;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.BeniDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.ImmagineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.UtenteDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.BeniBusiness;
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
            Beni b = BeniBusiness.getInstance().getBeneFromName(bene.getText());
            if (b != null) {
                Utente u = SessionHelper.getInstance().getUser();
                if (u != null) {
                    Venditore v = UtenteDAO.getInstance().findIfUserIsVenditore(u.getUsername());
                    if (v != null) {
                        if (v.getIdVenditore() == b.getVenditore_idVenditore()) {
                            immagini = new ArrayList<String>();
                            for (int ctr = 0; ctr < model.getSize(); ctr++) {
                                immagini.add((String) model.getElementAt(ctr)); //TODO: CHECK CAST
                            }
                            for (String path : immagini) {
                                Result k = this.sendImage(path, b.getIdBeni());
                            }
                            JOptionPane.showMessageDialog(null, model.getSize() + " immagini aggiunte al bene.");
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
        Result r = new Result();
        Immagine i = new Immagine();

        try {
            //File immagine = new File(path);
            i.setBeni_idBeni(beni_id);
            File f = new File(path);
            Result result = ImmagineDAO.getInstance().create(i, f);
            if (result.isSuccess() == true) {
                r.setSuccess(true);
                r.setMessage("Immagine aggiunta.");
                return r;
            } else {
                r.setSuccess(false);
                r.setMessage("Query non eseguita. Immagine non aggiunta.");
                return r;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        r.setSuccess(false);
        r.setMessage("Routine not completed.");
        return r;
    }

}
