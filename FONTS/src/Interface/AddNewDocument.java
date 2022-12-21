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
 * Dialog to add new document
 *
 * @author Víctor Mena Doz
 */

public class AddNewDocument implements ActionListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Represents the dialog window
     */
    private JDialog dialog;

    /**
     * Represents dialog's panel.
     */
    private JPanel panel;

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
    public AddNewDocument() {
        dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        try { //CHANGE VIEW
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

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
        panel.add(title, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(inputInitialTitle, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(author, c);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(inputAuthor, c);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(lang, c);
        c.gridx = 0;
        c.gridy = 6;
        panel.add(buttonOK, c);
        c.gridx = 3;
        c.gridy = 6;
        panel.add(buttonCancel, c);

        dialog.add(panel);
        dialog.setSize(300, 350);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - dialog.getWidth()) / 2;
        int y = (screenSize.height - dialog.getHeight()) / 2;

        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("OK")) {

        }
        else if(s.equals("Cancel")) {

        }
    }

    public static void main(String args[]) {
        AddNewDocument ad = new AddNewDocument();
    }
}
