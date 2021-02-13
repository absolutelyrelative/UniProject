package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.*;
import it.unisalento.pps.SimpleBooking.DAO.business.ImmagineBusiness;
import it.unisalento.pps.SimpleBooking.Model.*;
import it.unisalento.pps.SimpleBooking.util.MailHelper;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class general_beniView extends JFrame {

    private JLabel nome_label = new JLabel("Nome:");
    private JTextField nome = new JTextField(10);
    private JLabel descr_label = new JLabel("Descrizione:");
    private JTextField descr = new JTextField(50);
    private JLabel dataInizio_label = new JLabel("Inizio Disponibilità:");
    private JTextField dataInizio = new JTextField(9);
    private JLabel dataFine_label = new JLabel("Fine disponibilità:");
    private JTextField dataFine = new JTextField(9);
    private JLabel costi_label = new JLabel("Costo (€):");
    private JTextField costi = new JTextField(20);
    private JLabel addr_label = new JLabel("Indirizzo:");
    private JTextField addr = new JTextField(30);
    private JLabel tipoBene_label = new JLabel("Tipo:");
    private JTextField tipoBene = new JTextField(10);
    private JButton img_sx = new JButton("<");
    private JButton img_dx = new JButton(">");
    private JButton b_sx = new JButton("<");
    private JButton b_dx = new JButton(">");
    private ImageIcon immagine;
    private JLabel immagine_label;
    private int size;
    private int counter;

    ArrayList<Beni> beni;
    ArrayList<Immagine> immagini;
    private int img_size;
    private int img_counter;
    private Dimension panel_dimension;
    private Dimension image_rescale;

    private JCheckBox approvato = new JCheckBox("Approvato");
    private JCheckBox pubblicato = new JCheckBox("Pubblicato");
    private JButton pubblica = new JButton("Pubblica");
    private JButton non_pubblica = new JButton("Togli pubblicazione");
    private JButton rimuovi = new JButton("Rimuovi X");

    public general_beniView(ArrayList<Beni> beni) {
        this.beni = beni;
        panel_dimension = new Dimension(860, 860);
        //Panels
        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel south_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());

        pubblica.setForeground(Color.GREEN);
        non_pubblica.setForeground(Color.orange);
        rimuovi.setForeground(Color.RED);

        north_panel.add(b_sx);
        north_panel.add(b_dx);
        north_panel.add(pubblica);
        north_panel.add(non_pubblica);
        north_panel.add(rimuovi);
        center_panel.add(nome_label);
        center_panel.add(nome);
        center_panel.add(descr_label);
        center_panel.add(descr);
        center_panel.add(dataInizio_label);
        center_panel.add(dataInizio);
        center_panel.add(dataFine_label);
        center_panel.add(dataFine);
        center_panel.add(costi_label);
        center_panel.add(costi);
        center_panel.add(addr_label);
        center_panel.add(addr);
        center_panel.add(tipoBene_label);
        center_panel.add(tipoBene);
        center_panel.add(approvato);
        center_panel.add(pubblicato);
        nome.setEditable(false);
        descr.setEditable(false);
        dataInizio.setEditable(false);
        dataFine.setEditable(false);
        costi.setEditable(false);
        addr.setEditable(false);
        tipoBene.setEditable(false);
        approvato.setEnabled(false);
        pubblicato.setEnabled(false);


        immagine_label = new JLabel(immagine);
        immagine_label.setPreferredSize(new Dimension(200, 200));
        center_panel.add(immagine_label);

        south_panel.add(img_sx);
        south_panel.add(img_dx);

        b_dx.addActionListener(this::actionPerformed);
        b_sx.addActionListener(this::actionPerformed);
        img_dx.addActionListener(this::actionPerformed);
        img_sx.addActionListener(this::actionPerformed);
        pubblica.addActionListener(this::actionPerformed);
        non_pubblica.addActionListener(this::actionPerformed);
        rimuovi.addActionListener(this::actionPerformed);


        //Components
        descr.setPreferredSize(new Dimension(100, 100));

        //setVisible(true);
        setSize(panel_dimension); //TODO: Vorrei utilizzare pack()
        container_panel.add(north_panel, BorderLayout.NORTH);
        container_panel.add(center_panel, BorderLayout.CENTER);
        container_panel.add(south_panel, BorderLayout.SOUTH);
        getContentPane().add(container_panel);

        if (!beni.isEmpty()) {
            populateBeni(beni.get(0));
            size = beni.size();
            counter = 0; //Già fatto da dichiarazione ma per coerenza rimane qui
        }
    }

    public void populateBeni(Beni b) {
        nome.setText(b.getNome());
        descr.setText(b.getDescrizione());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dataInizio.setText(format.format(b.getData_Inizio()));
        dataFine.setText(format.format(b.getData_Fine()));
        costi.setText(String.valueOf(b.getCosto_pd()) + "pd, " + String.valueOf(b.getCosto_pw()) + "pw, " + String.valueOf(b.getCosto_pm()) + "pm");
        addr.setText(b.getAddr());

        if (b.getStato_Bene() != 0) {
            approvato.setSelected(true);
        } else {
            approvato.setSelected(false);
        }
        if (b.getPubblicazione() != 0) {
            pubblicato.setSelected(true);
        } else {
            pubblicato.setSelected(false);
        }


        immagini = ImmagineBusiness.getInstance().getImmaginiFromBene(b);
        img_size = immagini.size();
        img_counter = 0; //Per coerenza personale, lo lascio anche se messo in dichiarazione

        if (img_size == 0 || immagini.isEmpty()) {
            immagine_label.setVisible(false);
        } else {
            immagine_label.setVisible(true);
            Immagine i = immagini.get(0);
            immagine = this.creaImmaginedaByte(i.getData());
            Image scaled_img = immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            immagine = new ImageIcon(scaled_img);
            immagine_label.setIcon(immagine);
            immagine_label.setPreferredSize(new Dimension(400, 400));
        }

        //TODO: CHANGE
        //Questo è un pochino un mix illegale tra MVC & DAO, facciamo finta di nulla per ora ;)
        Tipo_Bene tb = Tipo_BeneDAO.getInstance().findById(b.getTipo_Bene_idTipo_Bene());
        if (tb != null) {
            tipoBene.setText(tb.getNome());
        }
    }

    //TODO: ADD CATCH FOR INDEX OUT OF BOUNDS
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b_dx) {
            if (counter < (size - 1)) {
                counter++;
                Beni b = beni.get(counter);
                populateBeni(b);
            } else {
                counter = 0;
                if (!beni.isEmpty()) {
                    Beni b = beni.get(counter);
                    populateBeni(b);
                }
            }
        }
        if (e.getSource() == b_sx) {
            if (counter == 0) {
                return;
            } else {
                counter--;
                if (!beni.isEmpty()) {
                    Beni b = beni.get(counter);
                    populateBeni(b);
                }
            }
        }
        if (e.getSource() == img_dx) {
            if (img_size == 0) {
                immagine_label.setVisible(false);
            } else {
                immagine_label.setVisible(true);
            }
            if (img_counter < (img_size - 1)) {
                img_counter++;
                immagine = this.creaImmaginedaByte(immagini.get(img_counter).getData());
                Image scaled_img = immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                immagine = new ImageIcon(scaled_img);
            } else {
                img_counter = 0;
                immagine = this.creaImmaginedaByte(immagini.get(img_counter).getData());
                Image scaled_img = immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                immagine = new ImageIcon(scaled_img);
            }
            immagine_label.setIcon(immagine);
        }
        if (e.getSource() == img_sx) {
            if (img_size == 0) {
                immagine_label.setVisible(false);
            } else {
                immagine_label.setVisible(true);
            }
            if (img_counter == 0) {
                immagine = this.creaImmaginedaByte(immagini.get(img_counter).getData());
                Image scaled_img = immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                immagine = new ImageIcon(scaled_img);
            } else {
                img_counter--;
                immagine = this.creaImmaginedaByte(immagini.get(img_counter).getData());
                Image scaled_img = immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                immagine = new ImageIcon(scaled_img);
            }
            immagine_label.setIcon(immagine);
        }
        if (e.getSource() == pubblica) {
            Beni b_toUpdate = beni.get(counter);
            if(b_toUpdate.getStato_Bene() == 1){
                BeniDAO.getInstance().publishBene(b_toUpdate);
                JOptionPane.showMessageDialog(null, "Bene pubblicato.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Il bene non è approvato.");
            }
            //pubblicato.setSelected(true);
        }
        if (e.getSource() == non_pubblica) {
            Beni b_toUpdate = beni.get(counter);
            BeniDAO.getInstance().unpublishBene(b_toUpdate);
            JOptionPane.showMessageDialog(null, "Bene rimosso da lista pubblica.");
            //pubblicato.setSelected(false);
        }
        if (e.getSource() == rimuovi) {
            Beni b_toRemove = beni.get(counter);
            Result r = BeniDAO.getInstance().delete(b_toRemove);
            beni.remove(counter);
            counter = 0;
            size = size - 1;
            if (r.isSuccess() == true) {
                Venditore v = VenditoreDAO.getInstance().findById(b_toRemove.getVenditore_idVenditore());
                if (v != null) {
                    Utente u = UtenteDAO.getInstance().findById(v.getUtente_idUtente());
                    if (u != null) {
                        new MailHelper().send(u.getEmail(), "SimpleBooking: Informazioni circa il tuo bene", "Il tuo Bene "
                                + b_toRemove.getNome() + " è stato rimosso.");
                        JOptionPane.showMessageDialog(null, "Bene rimosso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Errore.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Errore.");
            }
        }
    }

    public ImageIcon creaImmaginedaByte(byte[] data) {
        ImageIcon a = new ImageIcon(data);
        return a;
    }

    public void recalculate(ArrayList<Beni> beni) {
        this.beni = beni;
        if(!beni.isEmpty()){
            populateBeni(beni.get(0));
        }
        else{
            Beni b = new Beni();    //Empty Beni, will throw exceptions but it's not a big issue.
            b.setData_Inizio(new Date());
            b.setData_Fine(new Date());
            populateBeni(b);
        }
        size = beni.size();
        counter = 0; //Già fatto da dichiarazione ma per coerenza rimane qui
    }
}
