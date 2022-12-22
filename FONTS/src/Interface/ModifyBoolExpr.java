package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyBoolExpr extends JPanel{

    private PresentationController CtrlPres = PresentationController.getInstance();
    private JTextArea TxtExpr = new JTextArea();
    private JLabel l = new JLabel("Choose the boolean expression to modify");
    private JButton bModify = new JButton("Modify");

    private TitlesAuthorSearch titleAuthorSearch;

    private JFrame frame = new JFrame ("JFrame");

    private JComboBox cb;

    public ModifyBoolExpr() {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));
        setLayout(null);
        bModify.setBounds(280, 150, 150, 40);
        add(bModify);
        TxtExpr.setBounds(70, 160, 150, 20);
        add(TxtExpr);
        l.setBounds(115, 65, 330, 30);
        add(l);

        String[] list = {"Title list", "Authors list"}; //get list from presentation controller
        cb = new JComboBox(list);
        cb.setSize(new Dimension(40, 20));
        cb.setBounds(70, 120, 400, 20);
        add(cb);

        setVisible(true);


        ActionListener createExpression = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        TxtExpr.setText("");
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
