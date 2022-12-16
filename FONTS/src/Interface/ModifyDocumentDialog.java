package FONTS.src.Interface;

/**
 * @file ModifyDocumentDialog.java
 * Class <em>ModifyDocumentDialog</em>
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to show dialog for modify the information of a document
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */


public class ModifyDocumentDialog implements ActionListener {
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
     * Represents what user wants to modify of the document
     */
    private JComboBox change;

    /**
     * Represents field where user specifies title of the document.
     */
    private JTextField title;

    /**
     * Represents field where user specifies author's name.
     */
    private JTextField author;

    /**
     * Represents OK button.
     */
    private JButton buttonOK;

    /**
     * Represents Cancel button.
     */
    private JButton buttonCancel;

    /**
     * Constructor of ModifyDocumentDialog
     */
    public ModifyDocumentDialog() {

    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
    }
}
