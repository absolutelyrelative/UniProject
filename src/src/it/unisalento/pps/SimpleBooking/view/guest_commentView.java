package it.unisalento.pps.SimpleBooking.view;


import it.unisalento.pps.SimpleBooking.util.Comment;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;

public class guest_commentView extends JFrame {
    private JTree albero;
    private JScrollPane scrollpane;

    ArrayList<Comment> commenti_Parsed = new ArrayList<>();
    DefaultMutableTreeNode root;


    public guest_commentView(ArrayList<Comment> commenti) {
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


        JPanel north_panel = new JPanel(new FlowLayout());
        JPanel center_panel = new JPanel(new FlowLayout());
        JPanel container_panel = new JPanel(new BorderLayout());


        center_panel.add(scrollpane);
        albero.setPreferredSize(new Dimension(600, 600));
        scrollpane.setPreferredSize(new Dimension(600, 600));
        albero.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); //Per ascoltare gli eventi
        container_panel.add(north_panel, BorderLayout.NORTH);
        container_panel.add(center_panel, BorderLayout.CENTER);
        getContentPane().add(container_panel);


    }

}
