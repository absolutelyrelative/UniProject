package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.DAO.business.FeedbackBusiness;
import it.unisalento.pps.SimpleBooking.Model.Beni;
import it.unisalento.pps.SimpleBooking.util.Comment;
import it.unisalento.pps.SimpleBooking.util.Result;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class buyer_boughtcommentView extends JFrame {
    private Beni beni;
    private JTree albero;
    private JScrollPane scrollpane;
    private JTextArea risposta;
    private JLabel answer_label = new JLabel("Commento: ");
    private JButton answer_button = new JButton("Invia");
    private JLabel rating_label = new JLabel("Rating [X/5]: ");
    private JTextField rating = new JTextField(2);

    ArrayList<Comment> commenti_Parsed = new ArrayList<>();
    DefaultMutableTreeNode root;


    public buyer_boughtcommentView(ArrayList<Comment> commenti, Beni beni) {
        this.beni = beni;
        //Init frame
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setSize(800, 800);
        validate();
        doLayout();
        setVisible(true);

        //Parse comments
        this.commenti_Parsed = commenti;
        root = new DefaultMutableTreeNode("root");
        for (Comment comment : commenti_Parsed) {
            root.add(comment.getCombined());
        }
        albero = new JTree(root);
        scrollpane = new JScrollPane(albero);


        //Albero icons
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        ImageIcon imageIcon = new ImageIcon("comment_icon.png");
        renderer.setLeafIcon(imageIcon);
        renderer.setClosedIcon(imageIcon);
        renderer.setOpenIcon(imageIcon);
        albero.setCellRenderer(renderer);
        albero.setRootVisible(false);

        //Panel stuff
        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel south_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());

        //Components
        risposta = new JTextArea(4, 30);
        center_panel.add(scrollpane);
        south_panel.add(rating_label);
        south_panel.add(rating);
        south_panel.add(answer_label);
        south_panel.add(risposta);
        south_panel.add(answer_button);
        albero.setPreferredSize(new Dimension(600, 600));
        scrollpane.setPreferredSize(new Dimension(600, 600));
        albero.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); //Per ascoltare gli eventi
        container_panel.add(north_panel, BorderLayout.NORTH);
        container_panel.add(center_panel, BorderLayout.CENTER);
        container_panel.add(south_panel, BorderLayout.SOUTH);
        getContentPane().add(container_panel);

        // albero.addTreeSelectionListener(this::treeActionPerformed);
        // albero.addTreeWillExpandListener(treeWillExpandListener);
        answer_button.addActionListener(this::actionPerformed);

    }

    private void actionPerformed(ActionEvent e) {

        //Beni Listeners
        if (e.getSource() == answer_button) {
            int risposta = JOptionPane.showConfirmDialog(null, "Inviare il commento?", "", JOptionPane.YES_NO_OPTION);
            if (risposta == JOptionPane.OK_OPTION) { //ok
                try {
                    if (Integer.parseInt(rating.getText()) > 5 || Integer.parseInt(rating.getText()) < 0 || rating.getText().isEmpty() || rating.getText() == null) {
                        JOptionPane.showMessageDialog(null, "Errore: Inserisci un rating valido.");
                    } else {
                        if (this.risposta.getText().length() > 150) {
                            JOptionPane.showMessageDialog(null, "Errore: Commento troppo lungo.");
                        } else {
                            Result result = FeedbackBusiness.getInstance().createFeedback(Integer.parseInt(rating.getText()), this.beni, this.risposta.getText());
                            if (result.isSuccess()) {
                                JOptionPane.showMessageDialog(null, "Commento inviato.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Si è verificato un'errore. Si prega di riprovare.");
                            }
                        }
                    }
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Errore: Inserisci un rating valido.");
                }
            }
        }
    }
}
