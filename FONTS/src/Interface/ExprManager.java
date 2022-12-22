package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExprManager extends JPanel{
    private PresentationController CtrlPres = PresentationController.getInstance();

    private JButton bCreate = new JButton("Create new Boolean Expression");
    private JButton bModify = new JButton("Modify an existing Boolean Expression");

    private JFrame frame = new JFrame ("JFrame");


    public ExprManager(MainView mv) {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));
        setLayout(null);
        bCreate.setBounds(280, 100, 300, 40);
        add(bCreate);
        bModify.setBounds(280, 150, 300, 40);
        add(bModify);


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

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new ExprManager(new MainView()), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
