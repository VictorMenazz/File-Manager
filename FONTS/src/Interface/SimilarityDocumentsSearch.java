package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SimilarityDocumentsSearch extends JPanel {

    /**
     * Instance of the Presentation Controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * Title of the view
     */
    private JLabel titleView = new JLabel("Search similar documents");
    /**
     * Author label
     */
    private JLabel l = new JLabel("Author: ");
    /**
     * Title label
     */
    private JLabel l2 = new JLabel("Title: ");
    /**
     * Label of number of documents to match
     */
    private JLabel l3 = new JLabel("Desired documents: ");
    /**
     * Model for the authors combo box
     */
    private DefaultComboBoxModel<String> modelA;
    /**
     * Model for the titles combo box
     */
    private DefaultComboBoxModel<String> modelT;
    /**
     * Authors combo box
     */
    private JComboBox<String> authors;
    /**
     * Titles combo box
     */
    private JComboBox<String> titles;
    /**
     * TextField for the number of documents to search
     */
    private JFormattedTextField k;
    /**
     * Model for the results table
     */
    private DefaultTableModel model;
    /**
     * Table for the query results
     */
    private JTable table;
    /**
     * Button to make query
     */
    private JButton search = new JButton("Search");

    /**
     * Creates the view
     */
    public void load() {
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 20, 0);

        k.setValue(null);

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

    /**
     * Creator of the SimilarityDocumentsSearch
     */
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
                    Integer num = Integer.parseInt(k.getText());
                    HashMap<String, String> results = CtrlPres.toResultAppSearch(author, title, num);
                    showResults(title, author, results);
                }
            }
        };
        search.addActionListener(SearchDocuments);
    }

    /**
     * Shows the results obtained from the query
     * @param title Title of the selected document
     * @param author Author of the selected document
     * @param result Results from the query
     */
    public void showResults(String title, String author, HashMap<String, String> result){
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        JLabel label = new JLabel("Results similar to " + title + " by " + author);

        Object[][] data = new Object[result.size()][4];
        Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        int j = 0;
        for (String key : result.keySet()) {
            data[j][0] = j+1;
            data[j][1] = documentIcon;
            data[j][2] = result.get(key);
            data[j][3] = key;
            ++j;
        }

        String[] columnNames = {"Relevance", "Type", "Name", "Author"};
        model = new DefaultTableModel(data, columnNames) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(model) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoCreateRowSorter(true);
        table.setShowVerticalLines(false);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(50);


        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(500, 350));

        c.gridx = 0;
        c.gridy = 0;
        add(label, c);
        c.gridy = 1;
        add(tableScroll, c);

        updateUI();
        setVisible(true);
    }

    /**
     * Restarts the view
     */
    public void reset(){
        authors.setSelectedIndex(-1);
        titles.setSelectedIndex(-1);
        load();
        setVisible(true);
    }

}