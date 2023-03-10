package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @file AddNewDocumentDialog.java
 * Class <em>AddNewDocumentDialog</em>
 */

/**
 * Add New Document View
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */

public class AddNewDocument extends JPanel implements ActionListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Instance of the main view
     */
    private MainView mainView;

    /**
     * Represents OK button.
     */
    private JButton buttonOK;

    /**
     * Represents Cancel button.
     */
    private JButton buttonCancel;

    /**
     * Represents field where user specifies title of the document.
     */
    private JTextField inputInitialTitle;

    /**
     * Represents field where user specifies author's name.
     */
    private JTextField inputAuthor;

    /**
     * Represents field where user choose language
     */
    private JComboBox lang;

    /**
     * Default creator of dialog
     */
    public AddNewDocument(MainView mv) {
        mainView = mv;

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel title = new JLabel("Title: ");
        JLabel author = new JLabel("Author: ");
        inputInitialTitle = new JTextField(16);
        inputAuthor = new JTextField(16);

        lang = new JComboBox<>();
        lang.addItem("Spanish");
        lang.addItem("English");
        lang.addItem("Catalan");

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);

        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        c.gridx = 1;
        c.gridy = 0;
        add(inputInitialTitle, c);
        c.gridx = 0;
        c.gridy = 2;
        add(author, c);
        c.gridx = 1;
        c.gridy = 2;
        add(inputAuthor, c);
        c.gridx = 1;
        c.gridy = 4;
        add(lang, c);
        c.gridx = 1;
        c.gridy = 5;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 6;
        add(buttonOK, c);
        c.gridx = 3;
        c.gridy = 6;
        add(buttonCancel, c);

        setVisible(true);
    }

    public void reset() {
        inputAuthor.setText("");
        inputInitialTitle.setText("");

        setVisible(true);
    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("OK")) {
            ctrlPres.toDocument(inputAuthor.getText(), inputInitialTitle.getText(), (String)lang.getSelectedItem(), true, true);
            reset();
        }
        else if(s.equals("Cancel")) {
            JOptionPane.showMessageDialog(this, "Create document operation canceled");
        }
    }

    /*public static void main(String args[]) {
        AddNewDocument ad = new AddNewDocument(new MainView());
    }*/
}
