package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

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
     * Folders id used
     */
    private HashSet<Integer> ids;

    /**
     * Default creator of dialog
     */
    public AddNewFolder(MainView mv, int fatherid) {
        mainView = mv;
        ids = new HashSet<>();
        ids.add(0);

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel name = new JLabel("Name: ");
        inputInitialName = new JTextField(16);


        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);

        c.gridx = 0;
        c.gridy = 0;
        add(name, c);
        c.gridx = 1;
        c.gridy = 0;
        add(inputInitialName, c);
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel(" "), c);
        c.gridx = 1;
        c.gridy = 3;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 6;
        add(buttonOK, c);
        c.gridx = 3;
        c.gridy = 6;
        add(buttonCancel, c);

        setVisible(true);
    }

    public void reset() {
        inputInitialName.setText(" ");

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
            int aux = ids.size();
            ctrlPres.newFolder(inputInitialName.getText(), 0);
            JOptionPane.showMessageDialog(this, "Folder created");
            ids.add(aux);
            reset();
        }
        else if(s.equals("Cancel")) {
            JOptionPane.showMessageDialog(this, "Create Folder operation canceled");
        }
    }

    public static void main(String args[]) {
        AddNewFolder ad = new AddNewFolder(new MainView(), 0);
    }
}
