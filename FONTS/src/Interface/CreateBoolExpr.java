package FONTS.src.Interface;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @file CreateBoolExpr.java
 * Class <em>CreateBoolExpr</em>
 */

/**
 * Creation of a new boolean expression view
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */

public class CreateBoolExpr extends JPanel{
    /**
     * Instance of the presentation controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * Text Area for the boolean expression
     */
    private JTextArea txtExpr = new JTextArea();
    /**
     * Write boolean expression label
     */
    private JLabel l = new JLabel("Write the new boolean expression");
    /**
     * Button to create new boolean expression
     */
    private JButton bCreate = new JButton("Create");

    /**
     * Constructor of CreateBoolExpr
     */
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

    /**
     * Resets the view
     */
    public void reset() {
        txtExpr.setText("");
        setVisible(true);
    }

}

