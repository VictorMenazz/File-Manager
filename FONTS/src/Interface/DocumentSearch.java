package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DocumentSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get document");
    private JLabel l = new JLabel("Author");
    private JLabel l2 = new JLabel("Title");

    /*public void setAutor(String txtAutor) {
        this.txtAutor.setText(txtAutor);
        search.doClick();
    }*/

    private JComboBox authors;
    private JComboBox titles;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

    public DocumentSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        titleView.setBounds(175,5,200,30);
        add(titleView);

        //Obtener lista dsd controlador;
        ArrayList<String> list = CtrlPres.getAuthorsName();
        authors = new JComboBox(list.toArray());
        authors.setBounds(160, 100, 200, 20);
        add(authors);

        if (!list.isEmpty()){
            ArrayList<String> t = CtrlPres.getAuthorDocuments(list.get(authors.getSelectedIndex()));
            titles = new JComboBox(t.toArray());
            titles.setBounds(160, 150, 200, 20);
            add(titles);
        }


        l.setBounds(100,100,200,20);
        add(l);
        l2.setBounds(100, 150, 200, 20);
        add(l2);
        search.setBounds(200,200,100,20);
        add(search);

        setVisible(true);



        ActionListener SearchTitles = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulos = CtrlPres.buscarTitulos(txtAutor.getText());
                if (titulos == null) {
                    ferror();
                }
                else {
                    JPanel middlePanel = new JPanel();
                    JButton BOkay = new JButton("Aceptar");
                    middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Titulos del autor " + txtAutor.getText()));

                    JTextArea display = new JTextArea(20, 22);
                    display.setText(titulos);
                    display.setEditable(false);
                    JScrollPane scroll = new JScrollPane(display);
                    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    middlePanel.add(scroll);

                    JFrame frame = new JFrame();
                    frame.add(middlePanel);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);


                }
                ActionListener Salir = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        txtAutor.setText("");
                    }
                };
                //BOkay.addActionListener(Salir);

            }
        };
        //search.addActionListener(BuscarTitulos);
    }

    public void reset() {
        authors.setSelectedIndex(-1);
        titles.setSelectedIndex(-1);
        setVisible(true);
    }

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new DocumentSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
