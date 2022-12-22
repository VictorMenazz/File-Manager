package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchViewN extends JPanel {

    private PresentationController CtrlPres = PresentationController.getInstance();

    private JButton b1 = new JButton("Search titles by author");
    private JButton b2 = new JButton("Search authors by prefix");
    private JButton b3 = new JButton("Search concrete document");
    private JButton b4 = new JButton("Search similar documents");
    private JButton b5 = new JButton("Search relevant documents");
    private JButton b6 = new JButton("Search documents by boolean expression");

    private JFrame frame = new JFrame ("JFrame");


    public SearchViewN() {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));
        setLayout(null);
        b1.setBounds(280, 100, 300, 40);
        add(b1);
        b2.setBounds(280, 150, 300, 40);
        add(b2);
        b3.setBounds(280, 200, 300, 40);
        add(b3);
        b4.setBounds(280, 250, 300, 40);
        add(b4);
        b5.setBounds(280, 300, 300, 40);
        add(b5);
        b6.setBounds(280, 350, 300, 40);
        add(b6);


        setVisible(true);


        ActionListener chooseSearch = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
        b1.addActionListener(chooseSearch);
        b2.addActionListener(chooseSearch);
        b3.addActionListener(chooseSearch);
        b4.addActionListener(chooseSearch);
        b5.addActionListener(chooseSearch);
        b6.addActionListener(chooseSearch);

    }

    public void restart(){

    }

    /*public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new SearchViewN(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
