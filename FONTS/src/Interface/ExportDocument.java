package FONTS.src.Interface;

import FONTS.src.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExportDocument extends JPanel{

    private PresentationController CtrlPres = PresentationController.getInstance();

    private MainView mainView;

    private JLabel l = new JLabel("Author");
    private JLabel l2 = new JLabel("Title");

    private JLabel titleView = new JLabel("Select document to export:");
    private JComboBox chooser;
    private DefaultComboBoxModel<String> modelA;
    private DefaultComboBoxModel<String> modelT;

    private JComboBox<String> authors;
    private JComboBox<String> titles;

    private JButton export = new JButton("Export");

    public void load(){
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        ArrayList<String> list = CtrlPres.getAuthorsName();
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) array[i] = list.get(i);

        modelA = new DefaultComboBoxModel<>(array);
        authors = new JComboBox<>(modelA);
        authors.updateUI();

        modelT = new DefaultComboBoxModel<>();
        titles = new JComboBox<>(modelT);

        chooser = new JComboBox<>();
        chooser.addItem("txt");
        chooser.addItem("xml");
        chooser.addItem("json");
        chooser.setPreferredSize(new Dimension(70, 27));
        chooser.setMaximumSize(new Dimension(70, 27));
        chooser.setMinimumSize(new Dimension(70, 27));

        authors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = (String) authors.getSelectedItem();
                if(author != null) {
                    ArrayList<String> list2 = CtrlPres.toResultAutDocs(author);
                    String[] array2 = new String[list2.size()];
                    for (int i = 0; i < list2.size(); ++i) array2[i] = list2.get(i);

                    modelT = new DefaultComboBoxModel<>(array2);
                    titles.setModel(modelT);
                }

            }
        });

        revalidate();
        repaint();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(titleView, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(l, c);
        c.gridx = 1;
        c.gridy = 1;
        add(authors, c);
        c.gridx = 0;
        c.gridy = 2;
        add(l2, c);
        if (authors.getSelectedIndex() != -1) {
            c.gridx = 1;
            c.gridy = 2;
            add(titles, c);
        }
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Choose file type: "), c);
        c.gridx = 1;
        c.gridy = 3;
        add(chooser, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(export, c);

        authors.updateUI();
        if(titles != null) titles.updateUI();
        chooser.updateUI();

        updateUI();

        setVisible(true);
    }

    public ExportDocument(MainView mv){
        mainView = mv;

        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        load();
        setVisible(true);

        ActionListener SearchDocument = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == export && authors.getSelectedIndex() != -1 && titles.getSelectedIndex() != -1 && chooser.getSelectedIndex() != -1){
                    String a = (String) authors.getSelectedItem();
                    String t = (String) titles.getSelectedItem();
                    String type = (String) chooser.getSelectedItem();
                    String path;
                    // Create an object of JFileChooser class
                    JFileChooser j = new JFileChooser("f:");
                    j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    // Invoke the showsSaveDialog function to show the save dialog
                    int r = j.showSaveDialog(null);

                    if (r == JFileChooser.APPROVE_OPTION) {
                        path = j.getSelectedFile().getAbsolutePath();
                        CtrlPres.exportDocument(t, a, path, type);
                        mainView.setGo("Main");
                    }
                    // If the user cancelled the operation
                    else  {
                        JOptionPane.showMessageDialog(new JDialog(), "Export operation canceled");
                    }
                }
            }
        };
        export.addActionListener(SearchDocument);
    }

    public void reset() {
        if (authors != null) authors.setSelectedIndex(-1);
        if (titles != null) titles.setSelectedIndex(-1);
        if (chooser != null) titles.setSelectedIndex(-1);
        load();
        setVisible(true);
    }
}
