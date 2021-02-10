package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;

public class seller_addImagesView extends JFrame{

    private JLabel description = new JLabel("Seleziona una o più immagini per un bene indicato. Fai attenziona al nome.");
    private JTextField bene_name = new JTextField(10);
    private JButton seleziona = new JButton("Seleziona");
    private JButton salva = new JButton("Salva immagine");
    private JButton completa = new JButton("Completa operazione");
    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());

    public seller_addImagesView() {
        JPanel upper_panel = new JPanel();
        JPanel center_panel = new JPanel();
        JPanel containing_layout = new JPanel();
        containing_layout.setLayout(new BorderLayout());
        upper_panel.setLayout(new FlowLayout());
        center_panel.setLayout(new FlowLayout());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Immagini PNG e JPG", "jpg", "png");

        upper_panel.add(description);
        center_panel.add(bene_name);
        center_panel.add(seleziona);

        containing_layout.add(upper_panel, BorderLayout.NORTH);
        containing_layout.add(center_panel, BorderLayout.CENTER);
        getContentPane().add(containing_layout);

        fileChooser.setFileFilter(filter);
        seleziona.addActionListener(this::actionPerformed);


    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == seleziona) {
            int returnVal = fileChooser.showOpenDialog(this);
            System.out.println("Selezionato");

            /*if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());*/

            //Handle save button action.
        }
    }
}
