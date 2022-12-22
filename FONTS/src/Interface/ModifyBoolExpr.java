package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

public class ModifyBoolExpr extends JPanel{

    private PresentationController CtrlPres = PresentationController.getInstance();
    private JTextArea txtExpr = new JTextArea();

    private JLabel l = new JLabel("Write a new boolean expression or choose one to modify");
    private JButton bModify = new JButton("Modify");
    private JButton bCreate = new JButton("Create");
    private JButton bDelete = new JButton("Delete");

    private JList<String> list;

    private JFrame frame = new JFrame ("JFrame");


    public ModifyBoolExpr() {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);

        //String[] bExpr = CtrlPres.getBoolExpr();
        String[] bExpr = {"Hola", "Adeu"};
        list = new JList<>(bExpr);
        txtExpr.setPreferredSize(new Dimension(400, 40));
        list.setPreferredSize(new Dimension(400, 200));


        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        add(l, c);
        c.gridx = 0;
        c.gridy = 1;
        add(txtExpr, c);
        c.gridy = 2;
        c.gridheight = 3;
        add(list, c);
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





        /*setLayout(null);
        bModify.setBounds(280, 150, 150, 40);
        add(bModify);
        TxtExpr.setBounds(70, 160, 150, 20);
        add(TxtExpr);
        l.setBounds(115, 65, 330, 30);
        add(l);

        String[] list = CtrlPres.getBoolExpr();

        //String[] list = {"Title list", "Authors list"}; //get list from presentation controller
        cb = new JComboBox(list);
        cb.setSize(new Dimension(40, 20));
        cb.setBounds(70, 120, 400, 20);
        add(cb);*/

        setVisible(true);


        ActionListener createExpression = new ActionListener() {
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
                                list.updateUI();
                            } else JOptionPane.showMessageDialog(new JDialog(), creation);
                        }
                    }
                } else if (e.getSource() == bModify){
                    String modify = CtrlPres.modifyExpression(txtExpr.getText(), txtExpr.getText());
                    if (modify.equals("OK")){
                        txtExpr.setText("");
                        list.updateUI();
                    }
                } else if (e.getSource() == bDelete) {
                    String s = list.getSelectedValue();
                    if (CtrlPres.deleteExpression(s)) {
                        JOptionPane.showMessageDialog(new JDialog(), s + " has been deleted");
                        list.updateUI();
                    }

                }
            }

        };
        bModify.addActionListener(createExpression);
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
