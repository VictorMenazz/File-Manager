package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @file AddNewDocumentDialog.java
 * @brief Class <em>AddNewDocumentDialog</em>
 */

/**
 * @brief Dialog to add new document
 *
 * @author Víctor Mena Doz
 */

public class AddNewDocumentDialog implements ActionListener {
    /**
     * @brief Represents the dialog window
     */
    private JDialog dialog;

    /**
     * @brief Represents dialog's panel.
     */
    private JPanel panel;

    /**
     * @brief Represents OK button.
     */
    private JButton buttonOK;

    /**
     * @brief Represents Cancel button.
     */
    private JButton buttonCancel;

    /**
     * @brief Represents field where user specifies title of the document.
     */
    private JTextField inputInitialTitle;

    /**
     * @brief Represents field where user specifies author's name.
     */
    private JTextField inputAuthor;

    /**
     * @brief Represents field where user choose language
     */
    private JComboBox lang;

    /**
     * @brief Default creator of dialog
     */
    public AddNewDocumentDialog() {
        panel = new JPanel();

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
        inputInitialTitle = new JTextField();
        inputAuthor = new JTextField();

        title.add(inputInitialTitle);
        author.add(inputAuthor);

        lang = new JComboBox<>();
        lang.addItem("Spanish");
        lang.addItem("English");
        lang.addItem("Catalan");

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);

        panel.add(title);
        panel.add(author);
        panel.add(lang);

        dialog.add(panel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - dialog.getWidth()) / 2;
        int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }

    /**
     * @brief Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String args[]) {
        AddNewDocumentDialog ad = new AddNewDocumentDialog();
    }
}
