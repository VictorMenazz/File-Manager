package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

public class ModifyBoolExpr extends JPanel{

    private PresentationController CtrlPres = PresentationController.getInstance();
    private JTextArea txtExpr = new JTextArea();

    /**
     * Table model for File[]
     */
    private ListSelectionListener listSelectionListener;
    private JLabel l = new JLabel("Write a new boolean expression or choose one to modify");
    private JButton bModify = new JButton("Modify");
    private JButton bCreate = new JButton("Create");
    private JButton bDelete = new JButton("Delete");

    private JTable list;
    private DefaultTableModel model;

    private JFrame frame = new JFrame ("JFrame");


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

    private void setError(String error, String message) {
        JDialog invalidExpr = new JDialog(frame, message);
        invalidExpr.setBounds(800, 300, 400, 200);
        invalidExpr.setLayout(null);

        JLabel txtErr = new JLabel(error);
        txtErr.setBounds(80, 20, 400, 40);
        JButton BOkay = new JButton("OK");
        BOkay.setVisible(true);
        BOkay.setBounds(150, 110, 100, 30);
        invalidExpr.add(txtErr);
        invalidExpr.add(BOkay);
        invalidExpr.setVisible(true);
        frame.setResizable(false);

        ActionListener Close = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                invalidExpr.dispose();
                invalidExpr.setVisible(false);
            }
        };
        BOkay.addActionListener(Close);
    }

    public void reset() {
        txtExpr.setText("");
        reloadTable();
    }

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new ModifyBoolExpr(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
