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

    private JDialog dialog;

    /**
     * Represents dialog's panel.
     */
    private JPanel panel;

    private JButton buttonOk;

    private JButton buttonCancel;


    public ConfirmationDialog() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
    }
}
