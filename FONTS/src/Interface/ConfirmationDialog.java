package FONTS.src.Interface;

/**
 * @file ConfirmationDialog.java
 * Class <em>ConfirmationDialog</em>
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog to confirm something
 *
 * @author Júlia Alice Amenós Dien
 */
public class ConfirmationDialog implements ActionListener {
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
    private JButton buttonOk;

    /**
     * Represents Cancel button.
     */
    private JButton buttonCancel;

    /**
     * Constructor of ConfirmationDialog
     */
    public ConfirmationDialog() {

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
