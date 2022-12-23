package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SimilarityDocumentsSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Search similar documents");
    private JLabel l = new JLabel("Author: ");
    private JLabel l2 = new JLabel("Title: ");

    private JLabel l3 = new JLabel("Desired documents: ");

    private DefaultComboBoxModel<String> modelA;
    private DefaultComboBoxModel<String> modelT;

    private JComboBox<String> authors;
    private JComboBox<String> titles;

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

        authors.setPreferredSize(new Dimension(250, 20));
        titles.setPreferredSize(new Dimension(250, 20));
        k.setText("");


        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(titleView, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(l, c);
        c.gridx = 1;
        c.gridy = 1;
        add(authors, c);
        c.gridx = 0;
        c.gridy = 2;
        add(l2, c);
        if (authors.getSelectedIndex() != -1) {
            c.gridx = 1;
            c.gridy = 2;
            add(titles, c);
        }
        c.gridx = 0;
        c.gridy = 3;
        add(l3, c);
        c.gridx = 1;
        c.gridy = 3;
        add(k, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(search, c);

        revalidate();
        repaint();
        updateUI();
        setVisible(true);

    }

    public SimilarityDocumentsSearch(){
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        setLayout(new GridBagLayout());


        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);  // only allow integers
        formatter.setMinimum(0);  // set the minimum value
        formatter.setMaximum(100);  // set the maximum value
        formatter.setAllowsInvalid(false);  // don't allow invalid input
        k = new JFormattedTextField(formatter);
        k.setPreferredSize(new Dimension(50, 20));

        load();


        setVisible(true);



        ActionListener SearchDocuments = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search){
                    String author = (String) authors.getSelectedItem();
                    String title = (String) titles.getSelectedItem();
                    Integer num = Integer.parseInt(k.getText())
                    HashMap<String, String> results = CtrlPres.toResultAppSearch(author, title, num);
                    showResults(results);

                }
            }
        };
        search.addActionListener(SearchDocuments);
    }

    public void showResults(HashMap<String, String> results){

        //Tabla
    }

    public void reset(){
        authors.setSelectedIndex(-1);
        titles.setSelectedIndex(-1);
        load();
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