package FONTS.src.Interface;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateBoolExpr extends JPanel{
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JTextArea txtExpr = new JTextArea();
    private JLabel l = new JLabel("Write the new boolean expression");
    private JButton bCreate = new JButton("Create");

    private JFrame frame = new JFrame ("JFrame");

    public CreateBoolExpr() {

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);

        txtExpr.setPreferredSize(new Dimension(400, 40));


        c.gridx = 0;
        c.gridy = 0;
        add(l, c);
        c.gridx = 3;
        c.gridy = 4;
        add(bCreate, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 3;
        add(txtExpr, c);




        /*bCreate.setBounds(280,150,150,40);
        add(bCreate);
        TxtExpr.setBounds(80,80,400,20);
        add(TxtExpr);
        l.setBounds(80,65,330,30);
        add(l);*/
        setVisible(true);


        ActionListener createExpression = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        } else JOptionPane.showMessageDialog(new JDialog(), creation);
                    }
                }
            }
        };
        bCreate.addActionListener(createExpression);
    }


    public void reset() {
        txtExpr.setText("");
    }

    /*public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new CreateBoolExpr(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}

