package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DocumentSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get document");
    private JLabel l = new JLabel("Author");
    private JLabel l2 = new JLabel("Title");


    private DefaultComboBoxModel<String> modelA;
    private DefaultComboBoxModel<String> modelT;

    private JComboBox<String> authors;
    private JComboBox<String> titles;

    private JButton search = new JButton("Search");
    private JFrame frame = new JFrame ("JFrame");

    private void loadTitles(String author) {

        ArrayList<String> listTitles = CtrlPres.toResultAutDocs(author);
        String[] arrayTitles = new String[listTitles.size()];
        for (int i = 0; i < listTitles.size(); ++i) arrayTitles[i] = listTitles.get(i);

        modelT = new DefaultComboBoxModel<>(arrayTitles);
        titles = new JComboBox<>(modelT);
    }

    public void load(){
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 20, 0);


        ArrayList<String> list = CtrlPres.getAuthorsName();
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) array[i] = list.get(i);

        modelA = new DefaultComboBoxModel<>(array);
        authors = new JComboBox<>(modelA);

        if (authors.getSelectedIndex() >= 0) {
            String a = (String) authors.getSelectedItem();
            loadTitles(a);
        }

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(titleView, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(l, c);
        c.gridx = 1;
        c.gridy = 2;
        add(authors, c);
        c.gridx = 0;
        c.gridy = 3;
        add(l2);
        if (authors.getSelectedIndex() >= 0) {
            c.gridx = 1;
            c.gridy = 3;
            add(titles);
        }
        c.gridwidth = 2;
        add(search, c);

        authors.revalidate();
        authors.repaint();
        setVisible(true);

    }


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



        /*ActionListener SearchTitles = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulos = CtrlPres.SearchTitles(txtAutor.getText());
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
        };*/
        //search.addActionListener(BuscarTitulos);
    }

    public void reset() {
        if(authors != null)authors.setSelectedIndex(-1);
        if(titles != null) titles.setSelectedIndex(-1);
        load();
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
