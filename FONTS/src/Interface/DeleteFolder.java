package FONTS.src.Interface;

/**
 * @file DeleteFolderDialog.java
 * Class <em>DeleteFolderDialog</em>
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to show a confirmation dialog to delete a given folder, with all it's content.
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */

public class DeleteFolder extends JPanel implements ActionListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Represents OK button.
     */
    private JButton buttonOK;

    /**
     * Represents Cancel button.
     */
    private JButton buttonCancel;

    /**
     * Constructor of DeleteFolderDialog
     * @param folderID, identification of the folder
     */
    public DeleteFolder(int folderID) {
        
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
