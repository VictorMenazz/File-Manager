package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class BoolExprSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get similar documents");
    private JLabel l = new JLabel("Author");
    private JLabel l2 = new JLabel("Title");

    private JLabel l3 = new JLabel("Desired documents");

    private JTextField p;

    private JFormattedTextField k;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

    public BoolExprSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        titleView.setBounds(175,5,200,30);
        add(titleView);

        //Obtener lista dsd controlador;
        /*ArrayList<String> list = CtrlPres.getAuthorsName();
        authors = new JComboBox(list.toArray());
        authors.setBounds(160, 100, 200, 20);
        add(authors);

        if (!list.isEmpty()){
            ArrayList<String> t = CtrlPres.getAuthorDocuments(list.get(authors.getSelectedIndex()));
            titles = new JComboBox(t.toArray());
            titles.setBounds(160, 150, 200, 20);
            add(titles);
        }

        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);  // only allow integers
        formatter.setMinimum(0);  // set the minimum value
        formatter.setMaximum(100);  // set the maximum value
        formatter.setAllowsInvalid(false);  // don't allow invalid input
        k = new JFormattedTextField(formatter);


        l.setBounds(100,100,200,20);
        add(l);
        l2.setBounds(100, 150, 200, 20);
        add(l2);
        search.setBounds(200,300,100,20);
        add(search);
        l3.setBounds(100, 200, 200, 20);
        add(l3);
        k.setBounds(240, 200, 50, 20);
        add(k);

        setVisible(true);
*/


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

    public void reset() {
        setVisible(true);
    }

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new SimilarityDocumentsSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
