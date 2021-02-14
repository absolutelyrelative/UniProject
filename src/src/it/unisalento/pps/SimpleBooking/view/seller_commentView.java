package it.unisalento.pps.SimpleBooking.view;

import it.unisalento.pps.SimpleBooking.util.Comment;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class seller_commentView extends JFrame{
    private JTree albero;
    private JScrollPane scrollpane;
    private JTextArea risposta;
    private JLabel answer_label = new JLabel("Risposta: ");
    private JButton answer_button = new JButton("Invia");

    ArrayList<Comment> commenti_Parsed = new ArrayList<>();
    DefaultMutableTreeNode root;


    public seller_commentView(ArrayList<Comment> commenti) {
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
        for (Comment c : commenti_Parsed) {
            root.add(c.getCombined());
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
        risposta = new JTextArea(4,50);
        center_panel.add(scrollpane);
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
        if (e.getSource() == answer_button){
            int risposta = JOptionPane.showConfirmDialog(null,"Inviare la risposta?","",JOptionPane.YES_NO_OPTION);
            if(risposta == JOptionPane.OK_OPTION){ //ok
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) albero.getLastSelectedPathComponent();
                if(node != null){
                    //DOSTUFF
                }
            }
            else{
                return;
            }
        }
    }

    private void treeActionPerformed(TreeSelectionEvent e){
        //Preso da https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html#select

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) albero.getLastSelectedPathComponent();
        if(node == null){
            return;
        }
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()){
            //Il nodo è child
            JOptionPane.showMessageDialog(null, "leaf");
        }
        else{
            //Il nodo è parent
            String risposta = JOptionPane.showInputDialog(null, "Scrivi la risposta:","Rispondi al commento", JOptionPane.INFORMATION_MESSAGE);
            if (risposta == null) { //Anche se si preme 'Cancel' si attiva
                System.out.println("The user canceled");
            }
            else{

            }
            albero.setSelectionPath(null); //Resetta la selezione
        }
    }

    TreeWillExpandListener treeWillExpandListener = new TreeWillExpandListener() {
        public void treeWillCollapse(TreeExpansionEvent treeExpansionEvent)
                throws ExpandVetoException {

            TreePath path = treeExpansionEvent.getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            //Print the name of the node if toString() was implemented
            String data = node.getUserObject().toString();
            System.out.println("WillCollapse: " + data);

        }

        public void treeWillExpand(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {

            TreePath path = treeExpansionEvent.getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            //print the name of the node if toString was implemented
            String data = node.getUserObject().toString();
            System.out.println("WillExpand: " + data);

        }
    };

}
