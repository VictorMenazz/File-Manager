package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
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

public class SearchView {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Represents the window to show the information
     */
    private JFrame frame;

    /**
     * Represents button to
     */
    private JButton sBoolExp;

    private JButton sSimilarity;

    private JButton sRelevance;

    private JButton sAuthors;

    private JButton sTitles;


    /**
     * Constructor
     */

    public SearchView() {
        frame = new JFrame("Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setSize(1000, 750);
        frame.setVisible(true);

        //contingut frame

    }

    /**
     * Content to show when the SearchView initially starts
     */
    private void ini() {
        String[] types = {"Titles list", "Authors list", "Specific document", "Documents list"};

        JButton b1 = new JButton("Titles list");
        JButton b2 = new JButton("Authors list");
        //queda acabar de posar la resta per les queries


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



    public static void main(String args[]) {
        SearchView sv = new SearchView();
    }

}
