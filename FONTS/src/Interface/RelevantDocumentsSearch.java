package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @file RelevantDocumentsSearch.java
 * Class <em>RelevantDocumentsSearch</em>
 */

/**
 * View to make a query by relevance
 *
 * @author Júlia Alice Amenós Dien
 */

public class RelevantDocumentsSearch extends JPanel {

    /**
     * Instance of the presentation controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * Label for the title of the view
     */
    private JLabel titleView = new JLabel("Get relevant documents");
    /**
     * Label for the word to search
     */
    private JLabel l = new JLabel("Words to search: ");
    /**
     * Label for the number of documents to search
     */
    private JLabel l2 = new JLabel("Number of documents to search: ");
    /**
     * Label for the language of the documents searched
     */
    private JLabel l3 = new JLabel("Language: ");
    /**
     * TextField for the words searched
     */
    private JTextField p;
    /**
     * TextField for the number of documents to search
     */
    private JFormattedTextField k;
    /**
     * ComboBox for the language choosen
     */
    private JComboBox<String> lang;

    /**
     * Table for the results
     */
    private JTable table;
    /**
     * Model for the table
     */
    private DefaultTableModel model;
    /**
     * Button for the search
     */
    private JButton search = new JButton("Search");

    /**
     * Loads the query view
     */
    public void load() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        p.setText("");
        k.setValue(null);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(l, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(p, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(l2, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 2;
        add(k, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 3;
        add(l3, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 3;
        add(lang, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(search, c);

    }

    /**
     * Constructor for the RelevantDocumentsSearch
     */
    public RelevantDocumentsSearch(){
        setLayout(null);

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        //titleView.setBounds(175,5,200,30);
        //add(titleView);

        p = new JTextField();
        p.setPreferredSize(new Dimension(400, 20));

        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);  // only allow integers
        formatter.setMinimum(0);  // set the minimum value
        formatter.setMaximum(100);  // set the maximum value
        formatter.setAllowsInvalid(false);  // don't allow invalid input
        k = new JFormattedTextField(formatter);
        k.setPreferredSize(new Dimension(50, 20));

        lang = new JComboBox<>(new String[]{"Spanish", "Catalan", "English"});

        load();

        setVisible(true);



        ActionListener SearchResult = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search){
                    if (p.getText().length() != 0 && k.getText().length() != 0){
                        String language;
                        if (lang.equals("Spanish")) language = "ESP";
                        else if (lang.equals("Catalan")) language = "CAT";
                        else language = "ENG";

                        int num = Integer.parseInt(k.getText());

                        HashMap<String, String> aux = null;
                        try {
                            aux = CtrlPres.toResultDocQuery(p.getText(), language, num);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(new JOptionPane(), "FAIL");
                        }
                        if (aux.size() < num) JOptionPane.showMessageDialog(new JOptionPane(), "Not found the desired number of documents");
                        else {
                            showResults(aux);
                        }
                    } else JOptionPane.showMessageDialog(new JOptionPane(), "Fields cannot be empty");
                }

            }
        };
        search.addActionListener(SearchResult);
    }

    /**
     * Shows the results obtained from the query
     * @param result results of the documents obtained
     */
    public void showResults(HashMap<String, String> result) {
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        JLabel label = new JLabel("Results relevant documents:");

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
        load();
        setVisible(true);
    }
}
