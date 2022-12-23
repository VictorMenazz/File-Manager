package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SimilarityDocumentsSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Search similar documents");
    private JLabel l = new JLabel("Author: ");
    private JLabel l2 = new JLabel("Title: ");

    private JLabel l3 = new JLabel("Desired documents: ");

    private DefaultComboBoxModel<String> modelA;
    private DefaultComboBoxModel<String> modelT;

    private JComboBox authors;
    private JComboBox titles;

    private JFormattedTextField k;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

    public void load() {
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 20, 0);

        ArrayList<String> list = CtrlPres.getAuthorsName();
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) array[i] = list.get(i);

        modelA = new DefaultComboBoxModel<>(array);
        authors = new JComboBox<>(modelA);
        authors.updateUI();

        modelT = new DefaultComboBoxModel<>();
        titles = new JComboBox<>(modelT);

        authors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = (String) authors.getSelectedItem();
                if(author != null) {
                    ArrayList<String> list2 = CtrlPres.toResultAutDocs(author);
                    String[] array2 = new String[list2.size()];
                    for (int i = 0; i < list2.size(); ++i) array2[i] = list2.get(i);

                    modelT = new DefaultComboBoxModel<>(array2);
                    titles.setModel(modelT);
                }

            }
        });

        revalidate();
        repaint();

    }

    public SimilarityDocumentsSearch(){
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        setLayout(new GridBagLayout());


        //Obtener lista dsd controlador;
        ArrayList<String> list = CtrlPres.getAuthorsName();
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


        load();

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
        authors.setSelectedIndex(-1);
        titles.setSelectedIndex(-1);
        k.setText("");
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