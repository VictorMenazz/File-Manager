package FONTS.src.Interface;


import FONTS.src.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * @file DeleteDocument.java
 * Class <em>DeleteDocument</em>
 */

/**
 * View to delete a document
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */
public class DeleteDocument extends JPanel implements ActionListener {

    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Instance of Main View
     */
    private MainView mainView;

    /**
     * Author label
     */
    private JLabel l = new JLabel("Author");
    /**
     * Title label
     */
    private JLabel l2 = new JLabel("Title");
    /**
     * Title View label
     */
    private JLabel titleView = new JLabel("Select document to delete:");
    /**
     * Model for authors comboBox
     */
    private DefaultComboBoxModel<String> modelA;
    /**
     * Model for titles comboBox
     */
    private DefaultComboBoxModel<String> modelT;
    /**
     * Authors comboBox
     */
    private JComboBox<String> authors;
    /**
     * Titles comboBox
     */
    private JComboBox<String> titles;
    /**
     * Button to delete document
     */
    private JButton delete = new JButton("Delete");

    /**
     * Button to cancel
     */
    private JButton buttonCancel = new JButton("Cancel");

    /**
     * Constructor of DeleteDocument
     */
    public DeleteDocument(MainView mv) {
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
                if (e.getSource() == delete && authors.getSelectedIndex() != -1 && titles.getSelectedIndex() != -1){
                    String a = (String) authors.getSelectedItem();
                    String t = (String) titles.getSelectedItem();
                    ctrlPres.deleteDocument(a, t);
                    JOptionPane.showMessageDialog(new JDialog(), "File deleted successfully");
                    reset();
                }
            }
        };
        delete.addActionListener(SearchDocument);
    }

    /**
     * Resets the view
     */
    public void reset() {
        if (authors != null) authors.setSelectedIndex(-1);
        if (titles != null) titles.setSelectedIndex(-1);
        load();
        setVisible(true);
    }

    /**
     * Updates and loads the view
     */
    public void load(){
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        ArrayList<String> list = ctrlPres.getAuthorsName();
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) array[i] = list.get(i);

        modelA = new DefaultComboBoxModel<>(array);
        authors = new JComboBox<>(modelA);
        authors.updateUI();

        modelT = new DefaultComboBoxModel<>();
        titles = new JComboBox<>(modelT);

        authors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = (String) authors.getSelectedItem();
                if(author != null) {
                    ArrayList<String> list2 = ctrlPres.toResultAutDocs(author);
                    String[] array2 = new String[list2.size()];
                    for (int i = 0; i < list2.size(); ++i) array2[i] = list2.get(i);

                    modelT = new DefaultComboBoxModel<>(array2);
                    titles.setModel(modelT);
                }

            }
        });

        buttonCancel.addActionListener(this);

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
        c.gridy = 4;
        c.gridwidth = 2;
        add(delete, c);
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 2;
        add(buttonCancel, c);

        authors.updateUI();
        if(titles != null) titles.updateUI();

        updateUI();

        setVisible(true);
    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Cancel")) {
            mainView.setGo("Main");
        }
    }
}
