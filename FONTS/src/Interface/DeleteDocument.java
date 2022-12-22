package FONTS.src.Interface;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @file DeleteDocDialog.java
 * Class <em>DeleteDocDialog</em>
 */

/**
 * Dialog to delete document
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */
public class DeleteDocument implements ActionListener {

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
     * Constructor of DeleteDocDialog
     * @param title, title of the document to delete
     * @param author, author's name of the document to delete
     */
    public DeleteDocument(String title, String author) {


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
