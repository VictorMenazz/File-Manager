package FONTS.src.Interface;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file BoolExprSearch.java
 * Class <em>BoolExprSearch</em>
 */

/**
 * Boolean Expression Search
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */


public class BoolExprSearch extends JPanel {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * ListSelectionListener for the table
     */
    private ListSelectionListener listSelectionListener;
    /**
     * Title of the view
     */
    private JLabel titleView = new JLabel("Write a new Boolean Expression or choose an existing one");
    /**
     * Label "Title"
     */
    private JLabel l2 = new JLabel("Title");

    /**
     * TextField for the boolean expression
     */
    private JTextField boolExpr;
    /**
     * Model for the table
     */
    private DefaultTableModel model;
    /**
     * Table with results from the query
     */
    private JTable table;
    /**
     * Table with the historial of boolean expressions
     */
    private JTable list;
    /**
     * Button to search from the writed boolean expression
     */
    private JButton searchNew = new JButton("Search New");
    /**
     * Button to search from the selected boolean expression from the list
     */
    private JButton searchList = new JButton("Search from List");

    /**
     * Updates the content of the table
     */
    private void reloadTable() {
        String[] bExpr = CtrlPres.getBoolExpr();
        String[] col = {"Boolean Expression"};
        String[][] data = new String[bExpr.length][1];
        for (int i = 0; i < bExpr.length; i++) {
            data[i][0] = bExpr[i];
        }
        model.setDataVector(data, col);
        list.updateUI();
        updateUI();
        setVisible(true);
    }

    /**
     * Loads the query view
     */
    public void load(){
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        boolExpr.setText("");

        String[] bExpr = CtrlPres.getBoolExpr();
        String[] col = {"Boolean Expressions"};
        String[][] data = new String[bExpr.length][1];
        for (int i = 0; i < bExpr.length; i++) {
            data[i][0] = bExpr[i];
        }

        model = new DefaultTableModel(data, col);
        list = new JTable(model) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        list.setAutoCreateRowSorter(true);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.getSelectionModel().addListSelectionListener(listSelectionListener);
        list.setPreferredSize(new Dimension (200, 200));
        JScrollPane tableScroll = new JScrollPane(list);
        tableScroll.setPreferredSize(new Dimension(400, 200));

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(titleView, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(boolExpr, c);
        c.gridx = 0;
        c.gridy = 2;
        add(tableScroll, c);
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        add(searchNew, c);
        c.gridx = 3;
        c.gridy = 2;
        add(searchList, c);


    }

    /**
     * Creator of the BoolExprSearch
     */
    public BoolExprSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        boolExpr = new JTextField();
        boolExpr.setPreferredSize(new Dimension(400, 20));

        load();

        setVisible(true);


        ActionListener SearchResult = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == searchNew){
                    String expr = boolExpr.getText();
                    if (expr.isEmpty()) JOptionPane.showMessageDialog(new JOptionPane(), "Introduce Boolean Expression to match");
                    else {
                        try {
                            HashMap<String, String> results = CtrlPres.toResultboolExp(expr);
                            showResults(expr, results);
                        } catch(Exception e1){
                            JOptionPane.showMessageDialog(new JDialog(), e1.getMessage());
                        }
                    }
                } else if (e.getSource() == searchList){
                    Integer row = list.getSelectedRow();
                    Integer column = list.getSelectedColumn();
                    String expr = (String) list.getValueAt(row, column);
                    try {
                        HashMap<String, String> results = CtrlPres.toResultboolExp(expr);
                        showResults(expr, results);
                    } catch(Exception e2){
                        JOptionPane.showMessageDialog(new JDialog(), e2.getMessage());
                    }
                }
            }
        };
        searchNew.addActionListener(SearchResult);
        searchList.addActionListener(SearchResult);
    }

    /**
     * Show table results from the query
     * @param expr, Boolean expression to match
     * @param result, documents from the query
     */
    public void showResults(String expr, HashMap<String, String> result){
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Results for  " + expr);

        Object[][] data = new Object[result.size()][3];
        Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        int j = 0;
        for (String key : result.keySet()) {
            data[j][0] = documentIcon;
            data[j][1] = result.get(key);
            data[j][2] = key;
            ++j;
        }

        String[] columnNames = {"Type", "Name", "Author"};
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
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(500, 350));

        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        c.gridy = 1;
        add(tableScroll, c);

        updateUI();
        setVisible(true);
    }

    /**
     * Restarts the view
     */
    public void reset() {
        load();
        setVisible(true);
    }
}
