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
public class DeleteDocDialog implements ActionListener {

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

    /**
     * Constructor of DeleteDocDialog
     * @param title, title of the document to delete
     * @param author, author's name of the document to delete
     */
    public  DeleteDocDialog(String title, String author) {


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
    }
}
