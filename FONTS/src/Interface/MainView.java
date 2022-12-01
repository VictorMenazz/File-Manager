package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;

/**
 * FIRST VIEW WHEN USERS OPEN THE APP AND WHERE STRUCTURE OF FOLDERS AND DOCUMENTS IS SHOW
 */

public class MainView {

    private JFrame mainView;

    private JPanel firstPanel;

    private JButton newDocument;

    private JButton help;

    /**
     * MORE THINGS TO SHOW
     */

    //private PresentationController CtrlPres = PresentationController.getInstance();

    public MainView() {
        //create frame
        mainView = new JFrame("File Manager");
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainView.setSize(1000, 600);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - mainView.getWidth()) / 2;
        int y = (screenSize.height - mainView.getHeight()) / 2;
        mainView.setLocation(x, y);
        mainView.setVisible(true);
    }

    //Provisional Main
    public static void main(String args[]) {
        MainView mv = new MainView();
    }
}
