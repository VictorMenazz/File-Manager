package FONTS.src.Interface;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateBoolExpr extends JPanel{
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JTextArea TxtExpr = new JTextArea();
    private JLabel l = new JLabel("Write the new boolean expression");
    private JButton bCreate = new JButton("Create");

    private JFrame frame = new JFrame ("JFrame");

    public CreateBoolExpr() {

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        setLayout(null);
        bCreate.setBounds(280,150,150,40);
        add(bCreate);
        TxtExpr.setBounds(70,160,150,20);
        add(TxtExpr);
        l.setBounds(115,65,330,30);
        add(l);
        setVisible(true);


        ActionListener createExpression = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (TxtExpr.getText().length() == 0) {
                    setError("Error, una expresión vacía es incorrecta","Expresión incorrecta");
                }
                else {
                    if (CtrlPres.existeExpresion(TxtExpr.getText())) {
                        //si ya existe se avisa
                        setError("Error, ya existe la expresión","Expresión ya existente");
                    }
                    else if (!CtrlPres.expresionCorrecta(TxtExpr.getText())) ferror("Error, la sintaxis es incorrecta","Expresión incorrecta");
                    else {
                        CtrlPres.crearExpresion(TxtExpr.getText());
                        JDialog Creado =  new JDialog(frame, "La expresión se ha creado");
                        Creado.setBounds(800, 300, 400, 200);
                        Creado.setLayout(null);
                        JLabel txtErr = new JLabel("La expresión se ha creado");
                        txtErr.setBounds(80, 20, 400, 40);
                        JButton BOkay = new JButton("Aceptar");
                        BOkay.setVisible(true);
                        BOkay.setBounds(150, 110, 100, 30);
                        Creado.add(txtErr);
                        Creado.add(BOkay);
                        Creado.setVisible(true);
                        frame.setResizable(false);

                        ActionListener Salir = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                txtErr.setText("");
                                Creado.dispose();
                                Creado.setVisible(false);
                            }
                        };
                        BOkay.addActionListener(Salir);
                    }
                }*/
            }
        };
        bCreate.addActionListener(createExpression);
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

