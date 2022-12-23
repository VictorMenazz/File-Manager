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

public class BoolExprSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();

    private ListSelectionListener listSelectionListener;
    private JLabel titleView = new JLabel("Write a new Boolean Expression or choose an existing one");
    private JLabel l = new JLabel("");
    private JLabel l2 = new JLabel("Title");


    private JTextField boolExpr;

    private DefaultTableModel model;
    private JTable list;

    private JButton searchNew = new JButton("Search New");
    private JButton searchList = new JButton("Search from List");

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
                    try {
                        HashMap<String, String> results = CtrlPres.toResultboolExp(expr);
                        showResults(expr, results);
                    } catch(Exception e1){
                        JOptionPane.showMessageDialog(new JDialog(), e1.getMessage());
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


    public void showResults(String expr, HashMap<String, String> results){
        JLabel title = new JLabel("Results for  " + expr);

        //tabla

    }

    public void reset() {
        load();
        setVisible(true);
    }
}
