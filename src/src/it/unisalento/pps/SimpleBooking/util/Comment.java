package it.unisalento.pps.SimpleBooking.util;

import it.unisalento.pps.SimpleBooking.Model.Feedback;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class Comment {
    private static final int comment_maxlength = 75;
    private DefaultMutableTreeNode parent_node;
    private DefaultMutableTreeNode child_node;
    private Feedback parent_feedback;
    private Feedback child_feedback;

    public Comment() {
        this.parent_node = new DefaultMutableTreeNode();
        this.child_node = new DefaultMutableTreeNode();
        this.parent_feedback = new Feedback();
        this.child_feedback = new Feedback();
    }

    public DefaultMutableTreeNode getParent_node() {
        return parent_node;
    }


    public DefaultMutableTreeNode getChild_node() {
        return child_node;
    }


    public void setParent_node(String s) {
        //this.parent_comment.setUserObject(formatString(s, 0));
        this.parent_node.setUserObject(s);
    }

    public void setChild_node(String s) {
        //this.child_comment.setUserObject(formatString(s, 1));
        this.child_node.setUserObject(s);
    }

    public void setParent_feedback(Feedback f) {
        this.parent_feedback = f;
    }

    public void setChild_feedback(Feedback f) {
        this.child_feedback = f;
    }

    public Feedback getParent_feedback() {
        return parent_feedback;
    }

    public Feedback getChild_feedback() {
        return child_feedback;
    }

    public DefaultMutableTreeNode getCombined() {
        //if (child_node.getUserObject() != null) { //Altrimenti l'albero mostra un child vuoto
            parent_node.add(child_node);
       // }
        return parent_node;
    }

    //Sfortunatamente, non c'è modo di convertire senza aggiunte da ArrayList -> String
    //Aggiungi <html> e </html>.
    //Inoltre, se s.length() > (75)/2, inserisci un <br>.
    @Deprecated
    private String formatString(String s, int type) {
        ArrayList<Character> caratteri = new ArrayList<>();
        int halved_length = (int) (float) comment_maxlength / 2;
        int ctr = 0;

        for (char c : s.toCharArray()) {
            if (ctr == 0) {
                caratteri.add('<');
                caratteri.add('h');
                caratteri.add('t');
                caratteri.add('m');
                caratteri.add('l');
                caratteri.add('>');
                if (type == 0) { //Parent
                    caratteri.add('<');
                    caratteri.add('b');
                    caratteri.add('>');
                }
            }
            if (ctr == halved_length) {
                caratteri.add('<');
                caratteri.add('b');
                caratteri.add('r');
                caratteri.add('>');
            }
            caratteri.add(c);
            if (ctr == (s.length() - 1)) {
                if (type == 0) { //Parent
                    caratteri.add('<');
                    caratteri.add('\\');
                    caratteri.add('b');
                    caratteri.add('>');
                }
                caratteri.add('<');
                caratteri.add('\\');
                caratteri.add('h');
                caratteri.add('t');
                caratteri.add('m');
                caratteri.add('l');
                caratteri.add('>');
            }
            ctr++;
        }
        System.out.println(caratteri.toString().replaceAll(",", ""));
        return caratteri.toString();

    }
}
