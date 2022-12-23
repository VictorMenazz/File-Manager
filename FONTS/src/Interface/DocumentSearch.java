package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DocumentSearch extends JPanel {
    /**
     * Instance of the presentation controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * Label for the title of the view
     */
    private JLabel titleView = new JLabel("Get document");
    /**
     * Author label
     */
    private JLabel l = new JLabel("Author  ");
    /**
     * Title label
     */
    private JLabel l2 = new JLabel("Title  ");

    /**
     * Model for the authors combo box
     */
    private DefaultComboBoxModel<String> modelA;
    /**
     * Model for the titles combo box
     */
    private DefaultComboBoxModel<String> modelT;

    /**
     * Combobox with all authors
     */
    private JComboBox<String> authors;
    /**
     * Combobox with the titles from an author
     */
    private JComboBox<String> titles;

    /**
     * Button to make query
     */
    private JButton search = new JButton("Search");

    /**
     * Loads and shows the view
     */
    public void load(){
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
        c.gridwidth = 2;
        add(search, c);

        revalidate();
        repaint();
        updateUI();

        setVisible(true);
    }

    /**
     * Creator of the DocumentSearch
     */
    public DocumentSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        load();
        setVisible(true);

        ActionListener SearchDocument = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search && authors.getSelectedIndex() != -1 && titles.getSelectedIndex() != -1){
                    String a = (String) authors.getSelectedItem();
                    String t = (String) titles.getSelectedItem();
                    CtrlPres.toDocument(a, t, "null", false, true);
                }
            }
        };
        search.addActionListener(SearchDocument);
    }

    /**
     * Resets the view
     */
    public void reset() {
        if (authors != null) authors.setSelectedIndex(-1);
        if (titles != null) titles.setSelectedIndex(-1);
        load();
        setVisible(true);
    }

}
