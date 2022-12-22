package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @file AddNewFolderDialog.java
 * Class <em>AddNewFolderDialog</em>
 */

/**
 * Dialog to add new Folder
 *
 * @author Víctor Mena Doz
 */

public class AddNewFolder extends JPanel implements ActionListener {
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
     * Represents field where user specifies title of the Folder.
     */
    private JTextField inputInitialName;


    /**
     * Default creator of dialog
     */
    public AddNewFolder(MainView mv) {
        mainView = mv;

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        setLayout(new GridBagLayout());
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

        JLabel title = new JLabel("Name: ");
        inputInitialName = new JTextField(16);


        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);

        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        c.gridx = 1;
        c.gridy = 0;
        add(inputInitialName, c);
        c.gridx = 0;
        c.gridy = 6;
        add(buttonOK, c);
        c.gridx = 3;
        c.gridy = 6;
        add(buttonCancel, c);

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
            //CREATE FOLDER
            inputInitialName.setText(" ");
        }
        else if(s.equals("Cancel")) {
            JOptionPane.showMessageDialog(this, "Create Folder operation canceled");
        }
    }

    public static void main(String args[]) {
        AddNewFolder ad = new AddNewFolder(new MainView());
    }
}
