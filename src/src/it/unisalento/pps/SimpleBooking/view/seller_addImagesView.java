package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.Listener.sendImageListener;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class seller_addImagesView extends JFrame {

    private JLabel description = new JLabel("Seleziona una o più immagini per un bene indicato. Fai attenziona al nome.");
    private JLabel bene_name_label = new JLabel("Nome bene:");
    private JTextField bene_name = new JTextField(10);
    private JLabel lista_immagini_label = new JLabel("Lista immagini selezionate:");
    private JList lista_immagini;
    private JButton seleziona = new JButton("Seleziona immagine");
    private JButton completa = new JButton("Invia immagini");
    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
    ArrayList<String> image_path = new ArrayList<String>();
    String[] final_tipi; // = new String[numero];
    /*
        String[] final_tipi = new String[tipi.size()];
        tipi.toArray(final_tipi);
        tipo_bene_list = new JComboBox<String>(final_tipi);
     */

    DefaultListModel model = new DefaultListModel();
    private JScrollPane scroller;
    private Dimension selector_dimension = new Dimension(400, 120);
    private JButton rimuovi = new JButton("Rimuovi tutte le immagini");

    public seller_addImagesView() {
        JPanel upper_panel = new JPanel();
        JPanel center_panel = new JPanel();
        JPanel south_panel = new JPanel();
        JPanel containing_layout = new JPanel();
        containing_layout.setLayout(new BorderLayout());
        upper_panel.setLayout(new FlowLayout());
        center_panel.setLayout(new FlowLayout());
        south_panel.setLayout(new FlowLayout());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Immagini PNG e JPG", "jpg", "png");

        upper_panel.add(description);
        center_panel.add(bene_name_label);
        center_panel.add(bene_name);
        center_panel.add(lista_immagini_label);
        lista_immagini = new JList(model);
        lista_immagini.setVisibleRowCount(10); //TODO: COMPUTE AUTOMATICALLY USING [DIMENSION.GETHEIGHT / {FONT.SIZE}] (?)
        lista_immagini.setLayoutOrientation(JList.VERTICAL);
        lista_immagini.setPreferredSize(selector_dimension);
        lista_immagini.setAutoscrolls(true);
        scroller = new JScrollPane(lista_immagini);
        scroller.setPreferredSize(selector_dimension);


        completa.setForeground(Color.GREEN);
        rimuovi.setForeground(Color.RED);
        center_panel.add(lista_immagini);
        south_panel.add(seleziona);
        south_panel.add(completa);
        south_panel.add(rimuovi);

        containing_layout.add(upper_panel, BorderLayout.NORTH);
        containing_layout.add(center_panel, BorderLayout.CENTER);
        containing_layout.add(south_panel, BorderLayout.SOUTH);
        getContentPane().add(containing_layout);

        fileChooser.setFileFilter(filter);
        seleziona.addActionListener(this::actionPerformed);
        sendImageListener listener = new sendImageListener(bene_name, model);
        completa.addActionListener(listener);
        rimuovi.addActionListener(listener);


    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == seleziona) {
            int returnVal = fileChooser.showOpenDialog(this);
            System.out.println("Selezionato");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                model.addElement(file.getAbsolutePath());
            }

        }
    }
}
