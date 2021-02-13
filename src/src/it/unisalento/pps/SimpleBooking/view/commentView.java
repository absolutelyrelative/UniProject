package it.unisalento.pps.SimpleBooking.view;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class commentView extends JFrame {

    JTree jt;
    DefaultMutableTreeNode nodo1 = new DefaultMutableTreeNode("<html>AAAAAAAA<br>AAAAAAAAA</html>");
    DefaultMutableTreeNode nodo2;

    public commentView(){
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        ImageIcon imageIcon = new ImageIcon("comment_icon.png");
        renderer.setLeafIcon(imageIcon);
        renderer.setClosedIcon(imageIcon);
        renderer.setOpenIcon(imageIcon);
        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        nodo2 = new DefaultMutableTreeNode();
        nodo2.setUserObject("BB");
        nodo1.add(nodo2);
        DefaultMutableTreeNode nodo3 = new DefaultMutableTreeNode("<html>224124<br>23424242</html>");
        DefaultMutableTreeNode nodo4 = new DefaultMutableTreeNode("cc");
        nodo2.add(nodo4);
        nodo1.add(nodo3);
        jt = new JTree(nodo1);
        jt.setCellRenderer(renderer);
        jt.setRootVisible(false);
        jt.setPreferredSize(new Dimension(400,400));

        panel.add(jt);
        getContentPane().add(panel);

    }

}
