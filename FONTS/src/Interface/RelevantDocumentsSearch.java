package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RelevantDocumentsSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get relevant documents");
    private JLabel l = new JLabel("Words to search");
    private JLabel l2 = new JLabel("Number of documents to search");

    private JTextArea p;

    private JFormattedTextField k;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

    public RelevantDocumentsSearch(){
        setLayout(null);

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        titleView.setBounds(175,5,200,30);
        add(titleView);

        p = new JTextArea();


        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);  // only allow integers
        formatter.setMinimum(0);  // set the minimum value
        formatter.setMaximum(100);  // set the maximum value
        formatter.setAllowsInvalid(false);  // don't allow invalid input
        k = new JFormattedTextField(formatter);

        /*c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(l, c);
        c.gridx = 1;
        c.gridy = 0;
        add(p, c);
        c.gridx = 0;
        c.gridy = 1;
        add(l2, c);
        c.gridx = 1;
        c.gridy = 1;
        add(k, c);*/


        l.setBounds(100,100,200,20);
        add(l);
        l2.setBounds(100, 200, 250, 20);
        add(l2);
        search.setBounds(200,300,100,20);
        add(search);
        p.setBounds(100, 130, 290, 60);
        add(p);
        k.setBounds(340, 200, 50, 20);
        add(k);

        setVisible(true);



        ActionListener SearchTitles = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String titulos = CtrlPres.buscarTitulos(txtAutor.getText());
                if (titulos == null) {
                    ferror();
                }
                else {
                    JPanel middlePanel = new JPanel();
                    JButton BOkay = new JButton("Aceptar");
                    middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Titulos del autor " + txtAutor.getText()));

                    JTextArea display = new JTextArea(20, 22);
                    display.setText(titulos);
                    display.setEditable(false);
                    JScrollPane scroll = new JScrollPane(display);
                    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    middlePanel.add(scroll);

                    JFrame frame = new JFrame();
                    frame.add(middlePanel);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);


                }
                ActionListener Salir = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        txtAutor.setText("");
                    }
                };
                //BOkay.addActionListener(Salir);
                */

            }
        };
        //search.addActionListener(BuscarTitulos);
    }

    public void reset(){
        k.setText("");
        p.setText("");
    }


    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new RelevantDocumentsSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
