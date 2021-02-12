package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.ImmagineDAO;
import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.ImmagineBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.Model.Immagine;
import it.unisalento.pps.SimpleBooking.Model.Tipo_Bene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class buyer_beniView extends JFrame {

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

    public buyer_beniView(ArrayList<Beni> beni) {
        this.beni = beni;
        //Panels
        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel south_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());

        north_panel.add(b_sx);
        north_panel.add(b_dx);
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
        nome.setEditable(false);
        descr.setEditable(false);
        dataInizio.setEditable(false);
        dataFine.setEditable(false);
        costi.setEditable(false);
        addr.setEditable(false);
        tipoBene.setEditable(false);

        immagine_label = new JLabel(immagine);
        immagine_label.setPreferredSize(new Dimension(200, 200));
        center_panel.add(immagine_label);

        south_panel.add(img_sx);
        south_panel.add(img_dx);

        b_dx.addActionListener(this::actionPerformed);
        b_sx.addActionListener(this::actionPerformed);
        img_dx.addActionListener(this::actionPerformed);
        img_sx.addActionListener(this::actionPerformed);


        //Components
        descr.setPreferredSize(new Dimension(100, 100));

        setVisible(true);
        setSize(new Dimension(860, 860)); //TODO: Vorrei utilizzare pack()
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


        immagini = ImmagineBusiness.getInstance().getImmaginiFromBene(b);
        Immagine i = immagini.get(0);
        img_size = immagini.size();
        img_counter = 0; //Per coerenza personale, lo lascio anche se messo in dichiarazione
        immagine = this.creaImmaginedaByte(i.getData());

        Image scaled_img = immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        immagine = new ImageIcon(scaled_img);
        immagine_label.setIcon(immagine);
        immagine_label.setPreferredSize(new Dimension(400, 400));

        //TODO: CHANGE
        //Questo è un pochino un mix illegale tra MVC & DAO, facciamo finta di nulla per ora ;)
        Tipo_Bene tb = Tipo_BeneDAO.getInstance().findById(b.getTipo_Bene_idTipo_Bene());
        if (tb != null) {
            tipoBene.setText(tb.getNome());
        }
        if (img_size == 0) {
            immagine_label.setVisible(false);
        } else {
            immagine_label.setVisible(true);
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
                Beni b = beni.get(counter);
                populateBeni(b);
            }
        }
        if (e.getSource() == b_sx) {
            if (counter == 0) {
                return;
            } else {
                counter--;
                Beni b = beni.get(counter);
                populateBeni(b);
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
    }

    public ImageIcon creaImmaginedaByte(byte[] data) {
        ImageIcon a = new ImageIcon(data);
        return a;
    }
}
