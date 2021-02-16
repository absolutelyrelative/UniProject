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
    private Dimension panel_dimension;
    private Dimension image_rescale;

    private JCheckBox approvato = new JCheckBox("Approvato"); //Inutilizzato. TODO: Remove
    private JCheckBox pubblicato = new JCheckBox("Pubblicato"); //Inutilizzato. TODO: Remove
    private JButton book = new JButton("Prenota");
    private JButton commenti = new JButton("Mostra Commenti");
    private JLabel from = new JLabel("da");
    private JDatePickerImpl datePicker_inizio;
    private JLabel to = new JLabel("a");
    private JDatePickerImpl datePicker_fine;

    public buyer_beniView(ArrayList<Beni> beni) {
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

        this.beni = beni;
        panel_dimension = new Dimension(860, 860);
        //Panels
        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel south_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());
        book.setForeground(Color.GREEN);

        north_panel.add(b_sx);
        north_panel.add(b_dx);
        north_panel.add(book);
        north_panel.add(from);
        north_panel.add(datePicker_inizio);
        north_panel.add(to);
        north_panel.add(datePicker_fine);
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
        //center_panel.add(approvato);
        //center_panel.add(pubblicato);
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
        south_panel.add(commenti);

        b_dx.addActionListener(this::actionPerformed);
        b_sx.addActionListener(this::actionPerformed);
        img_dx.addActionListener(this::actionPerformed);
        img_sx.addActionListener(this::actionPerformed);
        book.addActionListener(this::actionPerformed);
        commenti.addActionListener(this::actionPerformed);


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
        if (e.getSource() == commenti){
            if(!beni.isEmpty()) {
                Beni b = beni.get(counter);
                ArrayList<Comment> sorted = FeedbackBusiness.getInstance().getFormattedFeedbackfromBeniId(b.getIdBeni());
                new seller_commentView(sorted);
            }
            else{
                JOptionPane.showMessageDialog(null, "Errore: Non ci sono beni.");
            }
        }
        if (e.getSource() == book){
            //Crea un Line Item
            //Formatta data
            //MySQL Date Syntax should be YYYY-MM-DD
            Date Data_Inizio = new Date();
            Date Data_Fine = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Data_Inizio = format.parse(datePicker_inizio.getJFormattedTextField().getText());
                Data_Fine = format.parse(datePicker_fine.getJFormattedTextField().getText());
                if (Data_Inizio.after(Data_Fine)) {
                    JOptionPane.showMessageDialog(null, "Assicurati che le date siano corrette. Data Inizio non può venire dopo Data Fine.");
                } else{
                    //Controllo coerenza data con il bene
                    Beni b = beni.get(counter);
                    if(Data_Inizio.before(b.getData_Inizio()) || Data_Inizio.after(b.getData_Fine()) || Data_Fine.after(b.getData_Fine()) || Data_Fine.before(b.getData_Inizio())){
                        JOptionPane.showMessageDialog(null, "Il Bene non è disponibile in queste date.");
                    }
                    else{
                        boolean ordered = OrdineBusiness.getInstance().isOrdered(b.getIdBeni());
                        if(ordered == true){
                            JOptionPane.showMessageDialog(null, "Il Bene è già stato ordinato. Controlla tra poco o selezionane un altro!");
                        }
                        else{
                            Result h = OrdineBusiness.getInstance().createOrdine(Data_Inizio,Data_Fine,b);
                            if(h.isSuccess()){
                                JOptionPane.showMessageDialog(null, "Ordine aggiunto. Puoi proseguire al pagamento.");
                                OrdineBusiness.getInstance().sendAlert(b.getIdBeni()); //CAN BE CHECKED FOR RESULT
                                PagamentoBusiness.getInstance().generatePagamento(b.getIdBeni()); //CAN BE CHECKED FOR RESULT
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Non è stato possibile creare l'ordine. Ricarica la lista dei beni.");
                            }
                        }
                    }
                }
            }catch (Exception h) {
                JOptionPane.showMessageDialog(null, "Errore durante conversione tipo. Controlla che i dati siano corretti.");
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
