package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @file ExprManager.java
 * Class <em>ExprManager</em>
 */

/**
 * View to manage boolean expressions
 *
 * @author Júlia Alice Amenós Dien
 */

public class ExprManager extends JPanel{
    /**
     * Instance of the presentation controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * Button to create new boolean expression
     */
    private JButton bCreate = new JButton("Create new Boolean Expression");
    /**
     * Button to modify a boolean expression
     */
    private JButton bModify = new JButton("Modify an existing Boolean Expression");


    /**
     * Creator of the ExprManager
     * @param mv
     */
    public ExprManager(MainView mv) {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        bCreate.setMinimumSize(new Dimension(300, 40));
        bCreate.setMaximumSize(new Dimension(300, 40));
        bCreate.setPreferredSize(new Dimension(300, 40));
        bModify.setMinimumSize(new Dimension(300, 40));
        bModify.setMaximumSize(new Dimension(300, 40));
        bModify.setPreferredSize(new Dimension(300, 40));

        /*bCreate.setBackground(Color.decode("#8516f5"));
        bModify.setBackground(Color.decode("#8516f5"));

        bCreate.setForeground(Color.white);
        bModify.setForeground(Color.white);*/

        c.gridx = 0;
        c.gridy = 0;
        add(bCreate, c);
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 2;
        add(bModify, c);

        setVisible(true);

        ActionListener manageExpr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == bCreate){
                    mv.setGo("Create Boolean Expression");
                }
                else if(e.getSource() == bModify){
                    mv.setGo("Modify Boolean Expressions List");
                }
            }

        };
        bModify.addActionListener(manageExpr);
        bCreate.addActionListener(manageExpr);
    }

    /**
     * Loads and shows the view
     */
    public void load() {
        setVisible(true);
    }


}
