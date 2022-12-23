package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

/**
 * @file ModifyBoolExpr.java
 * Class <em>ModifyBoolExpr</em>
 */

/**
 * View to create, modify or delete boolean expressions
 *
 * @author Júlia Alice Amenós Dien
 */

public class ModifyBoolExpr extends JPanel{
    /**
     * Instance of presentation controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * TextArea for the boolean Expression
     */
    private JTextArea txtExpr = new JTextArea();

    /**
     * ListSelectionListener for the table
     */
    private ListSelectionListener listSelectionListener;
    /**
     * Label of subheader
     */
    private JLabel l = new JLabel("Write a new boolean expression or choose one to modify");
    /**
     * Button to modify the boolean expression
     */
    private JButton bModify = new JButton("Modify");
    /**
     * Button to create the boolean expression
     */
    private JButton bCreate = new JButton("Create");
    /**
     * Button to delete the boolean expression
     */
    private JButton bDelete = new JButton("Delete");

    /**
     * List of boolean expressions
     */
    private JTable list;
    /**
     * Model for the table
     */
    private DefaultTableModel model;


    /**
     * Updates the content of the table
     */
    private void reloadTable() {
        String[] bExpr = CtrlPres.getBoolExpr();
        String[] col = {"Boolean Expressions"};
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
     * Constructor of ModifyBoolExpr
     */
    public ModifyBoolExpr() {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);

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
        JScrollPane tableScroll = new JScrollPane(list);
        tableScroll.setPreferredSize(new Dimension(400, 200));
        txtExpr.setPreferredSize(new Dimension(400, 40));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        add(l, c);
        c.gridx = 0;
        c.gridy = 1;
        add(txtExpr, c);
        c.gridy = 2;
        c.gridheight = 3;
        add(tableScroll, c);
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(bCreate, c);
        c.gridy = 5;
        c.gridx = 1;
        add(bModify, c);
        c.gridx = 2;
        c.gridy = 5;
        add(bDelete, c);

        setVisible(true);

        ActionListener manageExpressions = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bCreate){
                    if (txtExpr.getText().length() == 0) {
                        JOptionPane.showMessageDialog(new JDialog(), "Error, empty Boolean Expression");
                    }
                    else {
                        if (CtrlPres.existsExpression(txtExpr.getText())){
                            JOptionPane.showMessageDialog(new JDialog(), "Error, Boolean Expression already exists");
                        }
                        else {
                            String creation = CtrlPres.addExpression(txtExpr.getText());
                            if (creation.equals("OK")) {
                                JOptionPane.showMessageDialog(new JDialog(), "Boolean Expression has been created");
                                reset();
                            } else JOptionPane.showMessageDialog(new JDialog(), creation);
                        }
                    }
                } else if (e.getSource() == bModify){
                    if (txtExpr.getText().length() == 0) {
                        JOptionPane.showMessageDialog(new JDialog(), "Error, empty Boolean Expression");
                    }
                    else {
                        int row = list.getSelectedRow();
                        if(row == -1) JOptionPane.showMessageDialog(new JDialog(), "Error, not selected Boolean Expression to modify");
                        String modify = CtrlPres.modifyExpression((String)list.getValueAt(row, 0), txtExpr.getText());
                        if (modify.equals("OK")) {
                            JOptionPane.showMessageDialog(new JDialog(), "Modified Boolean Expression");
                            reset();
                        }
                    }
                } else if (e.getSource() == bDelete) {
                    Integer row = list.getSelectedRow();
                    Integer column = list.getSelectedColumn();
                    Object s = list.getValueAt(row, column);
                    String expr = s.toString();
                    if (CtrlPres.deleteExpression(expr)) {
                        JOptionPane.showMessageDialog(new JDialog(), s + " has been deleted");
                        reset();
                    }
                }
            }

        };
        bModify.addActionListener(manageExpressions);
        bCreate.addActionListener(manageExpressions);
        bDelete.addActionListener(manageExpressions);
    }


    /**
     * Resets the view
     */
    public void reset() {
        txtExpr.setText("");
        reloadTable();
    }

}
