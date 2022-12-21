package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @file SearchView.java
 * Class <em>Search View</em>
 */

/**
 * Class Search view for viewing many kinds of search
 *
 * @author Júlia Amenós Dien
 * @author Víctor Mena Doz
 */

public class SearchView implements ActionListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Represents the window to show the information
     */
    private CardLayout card;
    private JFrame frame;
    private JPanel cardPanel, p1, p2, p3, p4, p5, p6;

    /**
     * Buttons
     */
    private JButton b1, b2, b3, b4, b5, b6;

    /**
     * Constructor
     */

    public SearchView() {
        frame = new JFrame("Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        card = new CardLayout();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setSize(1000, 750);

        //Labels
        JLabel g = new JLabel("Choose a desired search");

        //Panels
        cardPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();

        //Buttons
        b1 = new JButton("Title list");
        b2 = new JButton("Authors list");
        b3 = new JButton("Find document");
        b4 = new JButton("Similarity");
        b5 = new JButton("Boolean");
        b6 = new JButton("Relevance");

        cardPanel.add(b1);
        cardPanel.add(b2);
        cardPanel.add(b3);
        cardPanel.add(b4);
        cardPanel.add(b5);
        cardPanel.add(b6);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        cardPanel.setLayout(card);
        cardPanel.add(p1, "Title list");
        cardPanel.add(p2, "Authors list");
        cardPanel.add(p2, "Find document");
        cardPanel.add(p2, "Similarity");
        cardPanel.add(p2, "Boolean");
        cardPanel.add(p2, "Relevance");


        frame.add(cardPanel);
        //frame.setContentPane(cardPanel);
        frame.setVisible(true);
    }



    /**
     * Content to show when the SearchView initially starts
     */
    private void ini() {
        String[] types = {"Titles list", "Authors list", "Specific document", "Documents list"};
    }


    //-----------DIALOGS----------//

    /**
     * Pops a dialog to let the user introduce an author for the query.
     */
    public void queryTitlesDialog() {



    }

    /**
     * Pops a dialog to let the user introduce an author prefix for the query.
     */
    public void queryAuthorsDialog() {

    }


    /**
     * Pops a dialog to let the user introduce a document to find it.
     */
    public void queryDocumentDialog() {

    }

    /**
     * Pops a dialog to let the user make their document similarity query.
     */
    public void documentSimilarityDialog() {

    }

    /**
     * Pops a dialog to let the user make their boolean expression query
     */
    public void documentBooleanDialog(){


    }

    /**
     * Pops a dialog to let the user make their word relevance query
     */
    public void documentRelevanceDialog(){


    }



    //-----------VIEW CONTENT TO SHOW----------//

    /**
     * Show a list with all the titles from the query
     */
    private void showTitlesList() {


    }

    /**
     * Show a list with all the authors from the query
     */
    private void showAuthorsList() {

    }

    /**
     * Show a list with all the documents from the query
     */
    private void showDocumentsList() {


    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (e.getSource() == b1) {
            // create a dialog Box
            JDialog d = new JDialog(frame, "dialog Box");

            // create a label
            JLabel l = new JLabel("this is a dialog box");

            d.add(l);

            // setsize of dialog
            d.setSize(100, 100);

            // set visibility of dialog
            d.setVisible(true);
        }
    }



    public static void main(String args[]) {
        SearchView sv = new SearchView();
    }

}
