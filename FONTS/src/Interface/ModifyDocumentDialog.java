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

    private JComboBox change;

    private JTextField title;

    private JTextField author;

    /**
     * Represents OK button.
     */
    private JButton buttonOK;

    /**
     * Represents Cancel button.
     */
    private JButton buttonCancel;

    public ModifyDocumentDialog() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
    }
}
