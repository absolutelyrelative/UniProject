package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.MySQL.Tipo_BeneDAO;
import it.unisalento.pps.SimpleBooking.DAO.business.FeedbackBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.ImmagineBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.OrdineBusiness;
import it.unisalento.pps.SimpleBooking.DAO.business.PagamentoBusiness;
import it.unisalento.pps.SimpleBooking.Model.*;
import it.unisalento.pps.SimpleBooking.util.Comment;
import it.unisalento.pps.SimpleBooking.util.DateLabelFormatter;
import it.unisalento.pps.SimpleBooking.util.Result;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class buyer_boughtbeniView extends JFrame {
    private JLabel nome_label = new JLabel("Nome:");
    private JTextField nome = new JTextField(10);
    private JLabel descr_label = new JLabel("Descrizione:");
    private JTextField descr = new JTextField(50);
    private JLabel dataInizio_label = new JLabel("Check-in:");
    private JTextField dataInizio = new JTextField(9);
    private JLabel dataFine_label = new JLabel("Check-out:");
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

    private JButton commenti = new JButton("Mostra Commenti");
    private JCheckBox pagato = new JCheckBox("Pagato");
    private JButton paga = new JButton("Paga");

    public buyer_boughtbeniView(ArrayList<Beni> beni) {
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

        this.beni = beni;
        panel_dimension = new Dimension(860, 860);
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
        center_panel.add(paga);
        center_panel.add(pagato);
        //center_panel.add(approvato);
        //center_panel.add(pubblicato);
        nome.setEditable(false);
        descr.setEditable(false);
        dataInizio.setEditable(false);
        dataFine.setEditable(false);
        costi.setEditable(false);
        addr.setEditable(false);
        tipoBene.setEditable(false);
        pagato.setEnabled(false);


        immagine_label = new JLabel(immagine);
        immagine_label.setPreferredSize(new Dimension(200, 200));
        center_panel.add(immagine_label);

        south_panel.add(img_sx);
        south_panel.add(img_dx);
        south_panel.add(commenti);

        b_dx.addActionListener(this::actionPerformed);
        b_sx.addActionListener(this::actionPerformed);
        img_dx.addActionListener(this::actionPerformed);
        img_sx.addActionListener(this::actionPerformed);
        commenti.addActionListener(this::actionPerformed);
        paga.addActionListener(this::actionPerformed);


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

    public void populateBeni(Beni beni) {
        nome.setText(beni.getNome());
        descr.setText(beni.getDescrizione());
        Ordine orderFromBeniID = OrdineBusiness.getInstance().getOrderFromBeniID(beni.getIdBeni());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (orderFromBeniID != null) {
            Pagamento pagamento = PagamentoBusiness.getInstance().getPagamentoFromOrder(orderFromBeniID);
            dataInizio.setText(format.format(orderFromBeniID.getData_Inizio()));
            dataFine.setText(format.format(orderFromBeniID.getData_Fine()));
            costi.setText(String.valueOf(orderFromBeniID.getImporto_Tot()));
            if (pagamento.getStato() == 1) {
                pagato.setSelected(true);
            } else {
                pagato.setSelected(false);
            }
        }

        addr.setText(beni.getAddr());

        immagini = ImmagineBusiness.getInstance().getImmaginiFromBene(beni);
        img_size = immagini.size();
        img_counter = 0; //Per coerenza personale, lo lascio anche se messo in dichiarazione

        if (img_size == 0 || immagini.isEmpty()) {
            immagine_label.setVisible(false);
        } else {
            immagine_label.setVisible(true);
            Immagine immagine = immagini.get(0);
            this.immagine = this.creaImmaginedaByte(immagine.getData());
            Image scaled_img = this.immagine.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            this.immagine = new ImageIcon(scaled_img);
            immagine_label.setIcon(this.immagine);
            immagine_label.setPreferredSize(new Dimension(400, 400));
        }

        //TODO: CHANGE
        //Questo è un pochino un mix illegale tra MVC & DAO, facciamo finta di nulla per ora ;)
        Tipo_Bene tipo_bene = Tipo_BeneDAO.getInstance().findById(beni.getTipo_Bene_idTipo_Bene());
        if (tipo_bene != null) {
            tipoBene.setText(tipo_bene.getNome());
        }
    }

    //TODO: ADD CATCH FOR INDEX OUT OF BOUNDS
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b_dx) {
            if (counter < (size - 1)) {
                counter++;
                Beni beni = this.beni.get(counter);
                populateBeni(beni);
            } else {
                counter = 0;
                if (!beni.isEmpty()) {
                    Beni beni = this.beni.get(counter);
                    populateBeni(beni);
                }
            }
        }
        if (e.getSource() == b_sx) {
            if (counter == 0) {
                return;
            } else {
                counter--;
                if (!beni.isEmpty()) {
                    Beni beni = this.beni.get(counter);
                    populateBeni(beni);
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
        if (e.getSource() == commenti) {
            if (!beni.isEmpty()) {
                Beni beni = this.beni.get(counter);
                ArrayList<Comment> sorted = FeedbackBusiness.getInstance().getFormattedFeedbackfromBeniId(beni.getIdBeni());
                new buyer_boughtcommentView(sorted, beni);
            } else {
                JOptionPane.showMessageDialog(null, "Errore: Non ci sono beni.");
            }
        }
        if (e.getSource() == paga) {
            Ordine orderFromBeniID = OrdineBusiness.getInstance().getOrderFromBeniID(beni.get(counter).getIdBeni());
            if (orderFromBeniID != null) {
                Pagamento pagamentoFromOrder = PagamentoBusiness.getInstance().getPagamentoFromOrder(orderFromBeniID);
                if (pagamentoFromOrder != null) {
                    new buyer_paymentView(beni.get(counter), pagamentoFromOrder);
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Pagamento non trovato.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Errore: Ordine non trovato.");
            }

        }
    }

    public ImageIcon creaImmaginedaByte(byte[] data) {
        ImageIcon imageIcon = new ImageIcon(data);
        return imageIcon;
    }

    public void recalculate(ArrayList<Beni> beni) {
        this.beni = beni;
        if (!beni.isEmpty()) {
            populateBeni(beni.get(0));
        } else {
            Beni beni1 = new Beni();    //Empty Beni, will throw exceptions but it's not a big issue.
            beni1.setData_Inizio(new Date());
            beni1.setData_Fine(new Date());
            populateBeni(beni1);
        }
        size = beni.size();
        counter = 0; //Già fatto da dichiarazione ma per coerenza rimane qui
    }
}
