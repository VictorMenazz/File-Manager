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
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get document");
    private JLabel l = new JLabel("Author");
    private JLabel l2 = new JLabel("Title");


    private DefaultComboBoxModel<String> modelA;
    private DefaultComboBoxModel<String> modelT;

    private JComboBox<String> authors;
    private JComboBox<String> titles;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

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

        authors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = (String) authors.getSelectedItem();
                if(author != null) {
                    ArrayList<String> listTitles = CtrlPres.toResultAutDocs(author);
                    if (modelT != null) modelT.removeAllElements();
                    for (int i = 0; i < listTitles.size(); ++i) modelT.addElement(listTitles.get(i));
                    titles.updateUI();
                }
            }
        });

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
        if (titles != null) {
            c.gridx = 1;
            c.gridy = 2;
            add(titles, c);
        }
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(search, c);

        authors.updateUI();
        if(titles != null) titles.updateUI();

        updateUI();

        setVisible(true);
    }


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
                if (e.getSource() == search && authors != null && titles != null){
                    String a = (String) authors.getSelectedItem();
                    String t = (String) titles.getSelectedItem();
                    CtrlPres.toDocument(a, t, "null", false, true);
                }
            }
        };
        search.addActionListener(SearchDocument);
    }

    public void reset() {
        if (authors != null) authors.setSelectedIndex(-1);
        if (titles != null) titles.setSelectedIndex(-1);
        load();
        setVisible(true);
    }

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new DocumentSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
