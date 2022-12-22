package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TitlesAuthorSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get documents from an author");
    private JLabel l = new JLabel("Author's name");

    /*public void setAutor(String txtAutor) {
        this.txtAutor.setText(txtAutor);
        search.doClick();
    }*/

    private JComboBox authors;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

    public TitlesAuthorSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        titleView.setBounds(165,5,200,30);
        add(titleView);


        ArrayList<String> list = CtrlPres.getAuthorsName();
        authors = new JComboBox(list.toArray());
        authors.setBounds(160, 100, 200, 20);
        add(authors);


        l.setBounds(25,100,200,20);
        add(l);
        search.setBounds(200,200,100,20);
        add(search);

        setVisible(true);



        ActionListener SearchTitles = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> titles = CtrlPres.getAuthorDocuments((String) authors.getSelectedItem());
                if (titles.isEmpty()){
                    JOptionPane.showMessageDialog(new JDialog(), "Error, Author does not have any Document");
                } else {

                }

                /*String titulos = CtrlPres.buscarTitulos(txtAutor.getText());
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
                */

            }
        };
        //search.addActionListener(BuscarTitulos);
    }


    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new TitlesAuthorSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
